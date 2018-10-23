package com.melonltd.firebase.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
		    .setDatabaseUrl("https://naber-20180622.firebaseio.com")
		    .build();
		FirebaseApp.initializeApp(options);
		Firestore db = FirestoreClient.getFirestore();
		
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("account");
		HashMap<String, Object> user = new HashMap<String, Object>();
		user.put("name", "EVAN");
		user.put("phoneNumber", "08374837493");
		
		
		ref.addValueEventListener(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				Object document = snapshot.getValue();
				System.out.print("addValueEventListener --> ::");
			    System.out.println(document);
			    Map<String,Map<String, String>> map = snapshot.getValue(Map.class);
				System.out.println("map" + map );
			}
			
			@Override
			public void onCancelled(DatabaseError error) {
				// TODO Auto-generated method stub
				
			}
		});
		
		ref.addListenerForSingleValueEvent(new ValueEventListener() {
		  @Override
		  public void onDataChange(DataSnapshot snapshot) {
		    Object document = snapshot.getValue();
		    System.out.print("addListenerForSingleValueEvent --> ::");
		    System.out.println(document);
		    
		  }

		  @Override
		  public void onCancelled(DatabaseError error) {
		  }
		});
			
			
//		ref.setPriorityAsync(user);
		ref.push().setValue(user, new CompletionListener() {

			@Override
			public void onComplete(DatabaseError error, DatabaseReference ref) {
				System.out.println(ref.getKey());
				System.out.println(ref.getPath());
				System.out.println(ref.getSpec());
				System.out.println(ref.getRepo());
				System.out.println(ref.getDatabase());
				System.out.println(ref.getRoot());
				System.out.println(ref.getParent());
			}
			
		});


		DocumentReference docRef = db.collection("orders").document();
		
	
		docRef.addSnapshotListener(new Executor() {
			
			@Override
			public void execute(Runnable command) {
				// TODO Auto-generated method stub
				
			}
		}, new EventListener<DocumentSnapshot>() {
			
			@Override
			public void onEvent(DocumentSnapshot value, FirestoreException error) {
				
				// TODO Auto-generated method stub
				
			}
		});
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


