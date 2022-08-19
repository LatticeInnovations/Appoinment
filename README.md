# Helthcare

- Initially we create either a spring boot maven or gradle project for java.
- I built this as a maven project with required dependencies.

**Project Description:**
    This project built with an idea to create an appointment by patient by checking doctor's availablity.

# Docto's entity
In our database we will have doctor's name, city, email, phone number and speciality.
City can have 3 values only i.e. Delhi, Noida, Faridabad
Speciality can have 4 values i.e. Orthopedic, Gynecology, Dermatology, ENT specialist
A doctor can be added or removed from the platform.

# Patient's entity
In our database we will have patient's name, city, email, phone number and symptom.
City can have any value
Symptom can have the following values only
Arthritis, Backpain, Tissue injuries (comes under Orthopedic speciality)
Dysmenorrhea (comes under Gynecology speciality)
Skin infection, skin burn (comes under Dermatology speciality)
Ear pain (comes under ENT speciality)
A patient can be added or removed from the platform.

# Suggesting Doctors
There will be another API that will accept patient ID, and suggest the doctors based out of the patient location and the symptom.
E.g. 1: If the patient ID that we received as request param in this API, that patient have Arthritis as symptom then all the doctors of that location who is an Orthopedic will be sent as the response, since Arthritis comes under Orthopedic speciality.
E.g. 2: If a patient have Eye pain then only ENT specialist doctor should be suggested.

Edge-Case 1: If there isn't any doctor on that location (i.e. outside Delhi, Noida, Faridabad), the response should be "We are still waiting to expand to your location".
Edge-Case 2: If there isn't any doctor for that symptom on that location, the response should be "There isn't any doctor present at your location for your symptom".
