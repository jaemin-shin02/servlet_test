package main.java.domain;

public class Member {
    private Long id;

    private String name;
    private String email;

    //댓글, 게시물 저장, 좋아요 등등


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public static Member createMember(String name, String email){
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);

        return member;
    }
}
