package shared;

import java.io.Serializable;

public interface UserInformation extends Serializable {
    public String getUsername();
    public String getPassword();
}
