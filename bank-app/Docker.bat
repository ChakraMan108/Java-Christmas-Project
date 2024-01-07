cls
cd "C:\Users\tanakhia\GitHub\Java-Christmas-Project\bank-app\target"
docker.exe container run -v bank-app-volume:/home/.bank-app-data -it --name bank-app bank-app