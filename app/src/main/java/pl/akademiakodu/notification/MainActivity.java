package pl.akademiakodu.notification;

import android.app.Notification;
import android.app.Notification.InboxStyle;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    // dojść czemu InBoxStyle nie działa!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    // od wersji JLB? można dodawać rzyciski do notyfikacji ale rzadko się tego używa w praktyce

    @OnClick(R.id.button)
    public void onButtonClick(){

        // PendingIntent to jest intent przystosowany do uruchomienie jakiegos activity lub serwisu
        // ustawiamy otwarcie czegos inego (activity) przy odpowiednich prawach - otwarcie activity po kliknieciu notyfikacji
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // korzystamy z InBoxStyle aby móc zbudować notyfikcaję składającą się z killku linijek
        // ustawiamy ten styl w Builderze później - metoda setStyle
        // pamiętać o zgodności klas Notification lub NotificationCompat bo sie nie skompiluje inaczej
        Notification.InboxStyle inBoxStyle = new Notification.InboxStyle();
        inBoxStyle.setBigContentTitle("Lekcje: ");
        inBoxStyle.addLine(" - SharedPreferences");
        inBoxStyle.addLine(" - Animacje");
        inBoxStyle.addLine(" - WebView");

        // korzystamy z buildera wiec mozemy dodawac po kropce
        Notification noty = new Notification.Builder(this)
                .setContentTitle("Nowa wiadomość")
                .setContentText("Przyszła do Ciebie nowa wiadomość")
                .setTicker("Nowa wiadomość ...")
                // uruchomienie pendingIntenta
                .setContentIntent(pendingIntent)
                // AutoCancel - po kliknieciu w notyfikacje znika ona z listy powiadomien automatycznie
                .setAutoCancel(true)
                // ikonka musi byc zeby mialo co wyswietlic w pasku notyfikacji :D
                .setSmallIcon(R.mipmap.ic_launcher)
                // ustawienie stylu inBox
                .setStyle(inBoxStyle)
                // ustawiamy priorytet notyfikacji na max zeby wyswietlila sie jako pierwsza jesli jest ich duzo
                .setPriority(Notification.PRIORITY_MAX)
                .build();
        // NotificationCompat z biblioteki supprtowej wspiera kompatybiność wsteczną aplikacji - stare wersje androida
        // mamy przygotowana notyfikacje ale musimy ja jeszcze opublikowac poprzez notification managera
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, noty);
    }
}
