package uz.com.platform.app.users;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserFetch {
    private Object certificateFetch;
    private Object departmentFetch;
    private Object positionFetch;
    private Object roleFetch;
    private Object permissionFetch;
}
