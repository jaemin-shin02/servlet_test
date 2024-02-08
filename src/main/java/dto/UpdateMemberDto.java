package main.java.dto;

public class UpdateMemberDto {
    Long id;
    String email;

    public UpdateMemberDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
