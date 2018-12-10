package in.elanic.elanicanandab.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Anand A <anandabktda@gmail.com/>
 * Models are classes built put data from JSON response.
 */

public class QuestionModel {

    @SerializedName("items")
    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {

        @SerializedName("owner")
        private OwnerBean owner;
        @SerializedName("link")
        private String link;
        @SerializedName("title")
        private String title;

        public OwnerBean getOwner() {
            return owner;
        }

        public void setOwner(OwnerBean owner) {
            this.owner = owner;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class OwnerBean {

            @SerializedName("profile_image")
            private String profileImage;
            @SerializedName("display_name")
            private String displayName;

            public String getProfileImage() {
                return profileImage;
            }

            public void setProfileImage(String profileImage) {
                this.profileImage = profileImage;
            }

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }
        }
    }

}
