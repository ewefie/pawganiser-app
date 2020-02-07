###Base URL: 
165.22.27.62:8090

###Users:

- POST 165.22.27.62:8090/api/users
- GET 165.22.27.62:8090/api/users/{userId}
- PUT 165.22.27.62:8090/api/users/{userId}
- DELETE 165.22.27.62:8090/api/users/{userId}

###Contacts
- POST 165.22.27.62:8090/api/users/{userId}/contacts
- GET 165.22.27.62:8090/api/users/{userId}/contacts
- GET 165.22.27.62:8090/api/users/{userId}/contacts/{contactId}
- PUT 165.22.27.62:8090/api/users/{userId}/contacts/{contactId}
- (Remove all contacts) DELETE 165.22.27.62:8090/api/users/{userId}/contacts
- (Remove contact by its ID) DELETE 165.22.27.62:8090/api/users/{userId}/contacts/{contactId}

###Pets:
- POST 165.22.27.62:8090/api/users/{userId}/pets
- GET 165.22.27.62:8090/api/users/{userId}/pets
- GET 165.22.27.62:8090/api/users/{userId}/pets/{petId}
- (Not available yet)PUT 165.22.27.62:8090/api/users/{userId}/pets/{petId}
- (Remove all pets) DELETE 165.22.27.62:8090/api/users/{userId}/pets
- (Remove pet by its ID) DELETE 165.22.27.62:8090/api/users/{userId}/pets/{petId}

###Treatments
- POST 165.22.27.62:8090/api/users/{userId}/pets/{petId}/treatments
- GET 165.22.27.62:8090/api/users/{userId}/pets/{petId}/treatments
- GET 165.22.27.62:8090/api/users/{userId}/pets/{petId}/treatments/{treatmentId}
- PUT 165.22.27.62:8090/api/users/{userId}/pets/{petId}/treatments/{treatmentId}
- (Remove all treatments by pet id) DELETE 165.22.27.62:8090/api/users/{userId}/pets/{petId}/treatments
- (Remove treatment by its ID) DELETE 165.22.27.62:8090/api/users/{userId}/pets/{petId}/treatments/{treatmentId}

###Medicines
- POST 165.22.27.62:8090/api/users/{userId}/pets/{petId}/medicines
- GET 165.22.27.62:8090/api/users/{userId}/pets/{petId}/medicines
- GET 165.22.27.62:8090/api/users/{userId}/pets/{petId}/medicines/{medicineId}
- PUT 165.22.27.62:8090/api/users/{userId}/pets/{petId}/medicines/{medicineId}
- (Remove all medicines by pet id) DELETE 165.22.27.62:8090/api/users/{userId}/pets/{petId}/medicines
- (Remove medicine by its ID) DELETE 165.22.27.62:8090/api/users/{userId}/pets/{petId}/medicines/{medicineId}

###Nutritions
- POST 165.22.27.62:8090/api/users/{userId}/pets/{petId}/nutritions
- GET 165.22.27.62:8090/api/users/{userId}/pets/{petId}/nutritions
- GET 165.22.27.62:8090/api/users/{userId}/pets/{petId}/nutritions/{nutritionId}
- PUT 165.22.27.62:8090/api/users/{userId}/pets/{petId}/nutritions/{nutritionId}
- (Remove all nutritions by pet id) DELETE 165.22.27.62:8090/api/users/{userId}/pets/{petId}/nutritions
- (Remove nutrition by its ID) DELETE 165.22.27.62:8090/api/users/{userId}/pets/{petId}/nutritions/{nutritionId}


