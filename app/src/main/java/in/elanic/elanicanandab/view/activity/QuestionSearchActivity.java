package in.elanic.elanicanandab.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;

import in.elanic.elanicanandab.R;
import in.elanic.elanicanandab.implement.QuestionPresenterImplement;
import in.elanic.elanicanandab.interactor.GetQuestionInteractImplement;
import in.elanic.elanicanandab.model.QuestionModel;
import in.elanic.elanicanandab.network.NetworkUtil;
import in.elanic.elanicanandab.presenter.QuestionActivityActions;
import in.elanic.elanicanandab.presenter.QuestionActivityPresenter;
import in.elanic.elanicanandab.view.adapter.QuestionAdapter;
import in.elanic.elanicanandab.view.interfaces.RecyclerItemClickListener;

/**
 * Created by Anand A <anandabktda@gmail.com/>
 * Activity for search questions with tags and display its list
 */

public class QuestionSearchActivity extends AppCompatActivity implements QuestionActivityActions {

    private SearchView svQuestionSearch;
    private RecyclerView rvQuestionList;
    private ProgressBar progressBar;
    private TextView tvNoContentAvailable;
    private QuestionActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initProgressBar();

        presenter = new QuestionPresenterImplement(this, new GetQuestionInteractImplement());
        if (NetworkUtil.isNetworkAvailable(QuestionSearchActivity.this)) {
            presenter.requestDataFromServer(getString(R.string.empty));
        } else {
            noInternetConnection();
        }

        svQuestionSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (NetworkUtil.isNetworkAvailable(QuestionSearchActivity.this)) {
                    presenter.onSearchSubmit(query);
                } else {
                    noInternetConnection();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (NetworkUtil.isNetworkAvailable(QuestionSearchActivity.this)) {
                    if (TextUtils.isEmpty(newText)) {
                        presenter.onSearchSubmit(getString(R.string.empty));
                    }
                } else {
                    noInternetConnection();
                }
                return true;
            }
        });

    }

    /**
     * Handle if Internet connection is not available
     */

    private void noInternetConnection() {
        rvQuestionList.setVisibility(View.GONE);
        tvNoContentAvailable.setVisibility(View.VISIBLE);
        tvNoContentAvailable.setText(getString(R.string.no_internet_connection));
        progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * Initialize views
     */

    private void initializeViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        svQuestionSearch = findViewById(R.id.sv_question_search);
        tvNoContentAvailable = findViewById(R.id.tv_no_content_available);
        rvQuestionList = findViewById(R.id.rv_question_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(QuestionSearchActivity.this);
        rvQuestionList.setLayoutManager(layoutManager);

    }

    /**
     * Initializing progressbar programmatically
     */

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.VISIBLE);

        this.addContentView(relativeLayout, params);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(List<QuestionModel.ItemsBean> noticeArrayList) {
        rvQuestionList.setVisibility(View.VISIBLE);
        tvNoContentAvailable.setVisibility(View.GONE);
        QuestionAdapter adapter = new QuestionAdapter(getApplicationContext(), noticeArrayList, recyclerItemClickListener);
        rvQuestionList.setAdapter(adapter);

    }

    @Override
    public void setBlankData() {
        rvQuestionList.setVisibility(View.GONE);
        tvNoContentAvailable.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        rvQuestionList.setVisibility(View.GONE);
        tvNoContentAvailable.setVisibility(View.VISIBLE);
        tvNoContentAvailable.setText(String.format(getString(R.string.string_joint), getString(R.string.network_error), throwable.getMessage()));
    }

    /**
     * RecyclerItem click event listener
     */

    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(QuestionModel.ItemsBean itemsBean) {
            Uri uri = Uri.parse(itemsBean.getLink());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        }

    };
}
