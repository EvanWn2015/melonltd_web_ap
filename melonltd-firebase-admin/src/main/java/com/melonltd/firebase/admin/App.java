package com.melonltd.firebase.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class App {
	public static void main(String[] args) {
		try {
			try {
				init();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void init() throws IOException, InterruptedException, ExecutionException {
		InputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(credentials)
		    .build();
		FirebaseApp.initializeApp(options);
		Firestore db = FirestoreClient.getFirestore();
		
		DocumentReference docRef = db.collection("orders").document();
	
		Map<String, Object> data = new HashMap<>();
		data.put("first", "Ada2");
		data.put("last", "Lovelace2");
		data.put("born", 1811);
		
		//asynchronously write data
		ApiFuture<WriteResult> result = docRef.set(data);
		// ...
		// result.get() blocks on response
		System.out.println("Update time : " + result.get().getUpdateTime());
		
		
		ApiFuture<QuerySnapshot> query = db.collection("orders").get();
		
	}
}


