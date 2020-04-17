package com.don.navigation.loan

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.don.navigation.R
import kotlinx.android.synthetic.main.fragment_loan.*

/**
 * A simple [Fragment] subclass.
 */
class LoanFragment : Fragment() {
    val channelId: String = "deeplink"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnLoan.setOnClickListener {
            var args = Bundle()
            args.putString("args", "Test Pinjaman")
            val deepLink = findNavController().createDeepLink()
                .setDestination(R.id.loanScreen)
                .setArguments(args)
                .createPendingIntent()


            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(NotificationChannel(
                    "deeplink", "Deep Links", NotificationManager.IMPORTANCE_HIGH))
            }

            val builder = NotificationCompat.Builder(
                btnLoan.context, "deeplink")
                .setContentTitle("Navigation")
                .setContentText("Deep link to Android")
                .setSmallIcon(R.drawable.ic_my_order)
                .setContentIntent(deepLink)
                .setAutoCancel(true)
            notificationManager.notify(0, builder.build())

        }
    }

}
