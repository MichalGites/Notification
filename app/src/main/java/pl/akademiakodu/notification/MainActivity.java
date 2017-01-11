package pl.akademiakodu.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onButtonClick(){

        // PendingIntent to jest intent przystosowany do uruchomienie jakiegos activity lub serwisu
        // ustawiamy otwarcie czegos inego (activity) przy odpowiednich prawach - otwarcie activity po kliknieciu notyfikacji
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // korzystamy z buildera wiec mozemy dodawac po kropce
        Notification noty = new Notification.Builder(this)
                .setContentTitle("Nowa wiadomość")
                .setContentText("Przyszła do Ciebie nowa wiadomość")
                .setTicker("Nowa wiadomość ...")
                // uruchomienie pendingIntenta
                .setContentIntent(pendingIntent)
                // AutoCancel - po kliknieciu w notyfikacje znika ona z listy powiadomien automatycznie
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        // NotificationCompat z biblioteki supprtowej wspiera kompatybiność wsteczną aplikacji - stare wersje androida
        // mamy przygotowana notyfikacje ale musimy ja jeszcze opublikowac poprzez notification managera
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, noty);
    }
}
