package main.java.domain;

public class Coment {
    private Long id;
    private Long postId;
    private Long memberId;

    private String content;

    private int likes;

    public static Coment createComent(Long postId, Long memberId, String content) {
        Coment coment = new Coment();
        coment.setPostId(postId);
        coment.setMemberId(memberId);
        coment.setContent(content);
        coment.likes = 0;

        return coment;
    }

    public Long getPostId() {
        return postId;
    }

    private void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getMemberId() {
        return memberId;
    }

    private void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void addLikes(){
        this.likes++;
    }

    public void unLikes(){
        this.likes--;
    }
}
