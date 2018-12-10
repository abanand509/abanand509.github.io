package in.elanic.elanicanandab.view.interfaces;

import in.elanic.elanicanandab.model.QuestionModel;

/**
 * Created by Anand A <anandabktda@gmail.com/>
 * list item click actions
 */
public interface RecyclerItemClickListener {
    void onItemClick(QuestionModel.ItemsBean question);
}