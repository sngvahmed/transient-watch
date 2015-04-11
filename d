[1mdiff --git a/app/src/main/java/transientwatch/com/transientwatch/Controller/DetailsController/DetailsActivity.java b/app/src/main/java/transientwatch/com/transientwatch/Controller/DetailsController/DetailsActivity.java[m
[1mindex ac6fcb2..9c4a213 100644[m
[1m--- a/app/src/main/java/transientwatch/com/transientwatch/Controller/DetailsController/DetailsActivity.java[m
[1m+++ b/app/src/main/java/transientwatch/com/transientwatch/Controller/DetailsController/DetailsActivity.java[m
[36m@@ -5,15 +5,18 @@[m [mimport android.os.Bundle;[m
 import android.view.Menu;[m
 import android.view.MenuItem;[m
 [m
[32m+[m[32mimport transientwatch.com.transientwatch.Model.Transient;[m
 import transientwatch.com.transientwatch.R;[m
 [m
 [m
 public class DetailsActivity extends ActionBarActivity {[m
[32m+[m[32m    Transient trans;[m
 [m
     @Override[m
     protected void onCreate(Bundle savedInstanceState) {[m
         super.onCreate(savedInstanceState);[m
         setContentView(R.layout.activity_details);[m
[32m+[m[32m        trans = (Transient) getIntent().getSerializableExtra("TransientItem");[m
     }[m
 [m
 [m
[1mdiff --git a/app/src/main/java/transientwatch/com/transientwatch/Controller/MainController/MainControllerFragment.java b/app/src/main/java/transientwatch/com/transientwatch/Controller/MainController/MainControllerFragment.java[m
[1mindex 1b75af1..d6113bd 100644[m
[1m--- a/app/src/main/java/transientwatch/com/transientwatch/Controller/MainController/MainControllerFragment.java[m
[1m+++ b/app/src/main/java/transientwatch/com/transientwatch/Controller/MainController/MainControllerFragment.java[m
[36m@@ -52,6 +52,7 @@[m [mpublic class MainControllerFragment extends Fragment {[m
                         }[m
                     });[m
                     Intent intent = new Intent(getActivity() , DetailsActivity.class);[m
[32m+[m[32m                    intent.putExtra("TransientItem" , transientData.get(position));[m
                     startActivity(intent);[m
                 }[m
             });[m
[1mdiff --git a/app/src/main/java/transientwatch/com/transientwatch/Model/Transient.java b/app/src/main/java/transientwatch/com/transientwatch/Model/Transient.java[m
[1mindex a47b2eb..b7da51b 100644[m
[1m--- a/app/src/main/java/transientwatch/com/transientwatch/Model/Transient.java[m
[1m+++ b/app/src/main/java/transientwatch/com/transientwatch/Model/Transient.java[m
[36m@@ -1,6 +1,8 @@[m
 package transientwatch.com.transientwatch.Model;[m
 [m
[31m-public class Transient {[m
[32m+[m[32mimport java.io.Serializable;[m
[32m+[m
[32m+[m[32mpublic class Transient implements Serializable {[m
 		[m
 	private String name;[m
     private String type;[m
