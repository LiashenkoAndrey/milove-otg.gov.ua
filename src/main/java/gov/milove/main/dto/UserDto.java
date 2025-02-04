package gov.milove.main.dto;

import gov.milove.main.domain.AdminMetadata;
import gov.milove.main.domain.AppUser;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    public UserDto(AdminMetadata adminMetadata, AppUser appUser) {
        this.adminMetadata = adminMetadata;
        this.appUser = appUser;
    }

    public UserDto(Boolean isRegistered, AdminMetadata adminMetadata, AppUser appUser) {
        this.isRegistered = isRegistered;
        this.adminMetadata = adminMetadata;
        this.appUser = appUser;
    }

    Boolean isRegistered;

    AdminMetadata adminMetadata;

    AppUser appUser;
}
