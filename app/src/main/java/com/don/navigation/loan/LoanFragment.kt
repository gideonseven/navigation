package com.don.navigation.loan

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
import androidx.navigation.fragment.findNavController

import com.don.navigation.R
import kotlinx.android.synthetic.main.fragment_loan.*

/**
 * A simple [Fragment] subclass.
 */
class LoanFragment : Fragment() {

    companion object {
        const val KEY_ARGS = "args"
        const val CHANNEL_ID = "deeplink"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tvLoan.text = arguments?.getString(KEY_ARGS)


        btnLoan.setOnClickListener {
            val args = Bundle()
            args.putString(KEY_ARGS, "Text yg di kirim " + etLoan.text.toString())
            val deepLink = findNavController().createDeepLink()
                .setDestination(R.id.loanScreen)
                .setArguments(args)
                .createPendingIntent()


            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                        CHANNEL_ID, "Deep Links", NotificationManager.IMPORTANCE_HIGH
                    )
                )
            }

            val builder = NotificationCompat.Builder(
                btnLoan.context, CHANNEL_ID
            )
                .setContentTitle("Navigation")
                .setContentText("Deep link to Android")
                .setSmallIcon(R.drawable.ic_payment)
                .setContentIntent(deepLink)
                .setAutoCancel(true)
            notificationManager.notify(0, builder.build())

        }
    }

}
