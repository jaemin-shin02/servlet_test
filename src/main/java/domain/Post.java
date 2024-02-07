package main.java.domain;

public class Post {
    private Long id;

    private Long memberId;
    private String title;
    private String content;
    private int likes;

    public static Post createPost(Long memberId, String title, String content) {
        Post post = new Post();
        post.setMemberId(memberId);
        post.setTitle(title);
        post.setContent(content);
        post.likes = 0;

        return post;
    }

    //Getter Setter

    public Long getMemberId() {
        return memberId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    private void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public void addLikes(){
        this.likes++;
    }

    public void unLikes(){
        this.likes--;
    }
}

