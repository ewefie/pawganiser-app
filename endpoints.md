###Base URL: 
localhost:8090

###Users:

- POST localhost:8090/users/me
- GET localhost:8090/users/me/
- PUT localhost:8090/users/me/
- DELETE localhost:8090/users/me/

###Contacts
- POST localhost:8090/users/me/{userId}/contacts
- GET localhost:8090/users/me/{userId}/contacts
- GET localhost:8090/users/me/{userId}/contacts/{contactId}
- PUT localhost:8090/users/me/{userId}/contacts/{contactId}
- (Remove all contacts) DELETE localhost:8090/users/me/{userId}/contacts
- (Remove contact by its ID) DELETE localhost:8090/users/me/{userId}/contacts/{contactId}

###Pets:
- POST localhost:8090/users/me/pets
- GET localhost:8090/users/me/pets
- GET localhost:8090/users/me/pets/{petId}
- (Not available yet)PUT localhost:8090/users/me/pets/{petId}
- (Remove all pets) DELETE localhost:8090/users/me/pets
- (Remove pet by its ID) DELETE localhost:8090/users/me/pets/{petId}

###Treatments
- POST localhost:8090/users/me/{userId}/pets/{petId}/treatments
- GET localhost:8090/users/me/{userId}/pets/{petId}/treatments
- GET localhost:8090/users/me/{userId}/pets/{petId}/treatments/{treatmentId}
- PUT localhost:8090/users/me/{userId}/pets/{petId}/treatments/{treatmentId}
- (Remove all treatments by pet id) DELETE localhost:8090/users/me/{userId}/pets/{petId}/treatments
- (Remove treatment by its ID) DELETE localhost:8090/users/me/{userId}/pets/{petId}/treatments/{treatmentId}

###Medicines
- POST localhost:8090/users/me/{userId}/pets/{petId}/medicines
- GET localhost:8090/users/me/{userId}/pets/{petId}/medicines
- GET localhost:8090/users/me/{userId}/pets/{petId}/medicines/{medicineId}
- PUT localhost:8090/users/me/{userId}/pets/{petId}/medicines/{medicineId}
- (Remove all medicines by pet id) DELETE localhost:8090/users/me/{userId}/pets/{petId}/medicines
- (Remove medicine by its ID) DELETE localhost:8090/users/me/{userId}/pets/{petId}/medicines/{medicineId}

###nutrients
- POST localhost:8090/users/me/{userId}/pets/{petId}/nutrients
- GET localhost:8090/users/me/{userId}/pets/{petId}/nutrients
- GET localhost:8090/users/me/{userId}/pets/{petId}/nutrients/{nutritionId}
- PUT localhost:8090/users/me/{userId}/pets/{petId}/nutrients/{nutritionId}
- (Remove all nutrients by pet id) DELETE localhost:8090/users/me/{userId}/pets/{petId}/nutrients
- (Remove nutrition by its ID) DELETE localhost:8090/users/me/{userId}/pets/{petId}/nutrients/{nutritionId}


