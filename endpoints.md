###Base URL: 
165.22.27.62:8090

###Users:

- POST 165.22.27.62:8090/api/users
- GET 165.22.27.62:8090/api/users/{userId}
- PUT 165.22.27.62:8090/api/users/{userId}
- DELETE 165.22.27.62:8090/api/users/{userId}

###Pets:
- POST 165.22.27.62:8090/api/users/{userId}/pets
- GET 165.22.27.62:8090/api/users/{userId}/pets
- GET 165.22.27.62:8090/api/users/{userId}/pets/{petId}
- (Not available yet)PUT 165.22.27.62:8090/api/users/{userId}/pets/{petId}
- (Remove all pets) DELETE 165.22.27.62:8090/api/users/{userId}/pets
- (Remove pet by its ID) DELETE 165.22.27.62:8090/api/users/{userId}/pets/{petId}

###Contacts
- POST 165.22.27.62:8090/api/users/{userId}/contacts
- GET 165.22.27.62:8090/api/users/{userId}/contacts
- GET 165.22.27.62:8090/api/users/{userId}/contacts/{contactId}
- PUT 165.22.27.62:8090/api/users/{userId}/contacts/{contactId}
- (Remove all contacts) DELETE 165.22.27.62:8090/api/users/{userId}/contacts
- (Remove contact by its ID) DELETE 165.22.27.62:8090/api/users/{userId}/contacts/{contactId}


