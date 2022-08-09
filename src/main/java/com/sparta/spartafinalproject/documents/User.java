<<<<<<< Updated upstream
package com.sparta.spartafinalproject.documents;public class User {
}
=======
package com.sparta.spartafinalproject.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class User {
    @Id
    private String id;

}

>>>>>>> Stashed changes
