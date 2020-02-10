###Base URL: 
pawganiser.sdacademy.xyz:8090

###Users:

- POST pawganiser.sdacademy.xyz:8090/api/users
- GET pawganiser.sdacademy.xyz:8090/api/users/
- PUT pawganiser.sdacademy.xyz:8090/api/users/
- DELETE pawganiser.sdacademy.xyz:8090/api/users/

###Contacts
- POST pawganiser.sdacademy.xyz:8090/api/users/{userId}/contacts
- GET pawganiser.sdacademy.xyz:8090/api/users/{userId}/contacts
- GET pawganiser.sdacademy.xyz:8090/api/users/{userId}/contacts/{contactId}
- PUT pawganiser.sdacademy.xyz:8090/api/users/{userId}/contacts/{contactId}
- (Remove all contacts) DELETE pawganiser.sdacademy.xyz:8090/api/users/{userId}/contacts
- (Remove contact by its ID) DELETE pawganiser.sdacademy.xyz:8090/api/users/{userId}/contacts/{contactId}

###Pets:
- POST pawganiser.sdacademy.xyz:8090/api/users/pets
- GET pawganiser.sdacademy.xyz:8090/api/users/pets
- GET pawganiser.sdacademy.xyz:8090/api/users/pets/{petId}
- (Not available yet)PUT pawganiser.sdacademy.xyz:8090/api/users/pets/{petId}
- (Remove all pets) DELETE pawganiser.sdacademy.xyz:8090/api/users/pets
- (Remove pet by its ID) DELETE pawganiser.sdacademy.xyz:8090/api/users/pets/{petId}

###Treatments
- POST pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/treatments
- GET pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/treatments
- GET pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/treatments/{treatmentId}
- PUT pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/treatments/{treatmentId}
- (Remove all treatments by pet id) DELETE pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/treatments
- (Remove treatment by its ID) DELETE pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/treatments/{treatmentId}

###Medicines
- POST pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/medicines
- GET pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/medicines
- GET pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/medicines/{medicineId}
- PUT pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/medicines/{medicineId}
- (Remove all medicines by pet id) DELETE pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/medicines
- (Remove medicine by its ID) DELETE pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/medicines/{medicineId}

###nutrients
- POST pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/nutrients
- GET pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/nutrients
- GET pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/nutrients/{nutritionId}
- PUT pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/nutrients/{nutritionId}
- (Remove all nutrients by pet id) DELETE pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/nutrients
- (Remove nutrition by its ID) DELETE pawganiser.sdacademy.xyz:8090/api/users/{userId}/pets/{petId}/nutrients/{nutritionId}


