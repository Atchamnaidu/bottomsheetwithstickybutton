package com.example.myapplication

import android.R
import android.os.Bundle
import android.view.Display
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityMainTempBinding
import com.example.myapplication.databinding.DetailsLayoutBinding
import com.example.myapplication.databinding.PromoLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingTemp: ActivityMainTempBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingTemp = ActivityMainTempBinding.inflate(layoutInflater)
        setContentView(bindingTemp.root)
        bindingTemp.temp.addView(binding.root)
        binding.rclist.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = RcAdapter(getData())
        }
        val display: Display = windowManager.defaultDisplay
        var btnHeight = 0
        val promoLayoutBinding = PromoLayoutBinding.inflate(layoutInflater)
        val detailsLayoutBinding = DetailsLayoutBinding.inflate(layoutInflater)
        binding.promocontainer.addView(promoLayoutBinding.root)
        binding.detailscontainer.addView(detailsLayoutBinding.root)
        binding.btnContinueConfirmPayment.run {
            measure(display.width, display.height)
            btnHeight = measuredHeight
        }
        var promoHeight: Int
        var detailsHeight: Int
        var optionsHeight: Int
        val topGap = 154
        binding.promocontainer.run {
            measure(display.width, display.height)
            promoHeight = measuredHeight
        }
        binding.rclist.run {
            measure(display.width, display.height)
            optionsHeight = measuredHeight
        }
        binding.detailscontainer.run {
            measure(display.width, display.height)
            detailsHeight = measuredHeight
        }
        var notchCardHeightWithOutPaymentDetails: Int
        var notchCardHeightWithPaymentDetails: Int
        binding.rootContainer.run {
            val params = layoutParams as FrameLayout.LayoutParams
            params.setMargins(0, topGap, 0, 0)
            layoutParams = params
        }

        notchCardHeightWithPaymentDetails = promoHeight + optionsHeight + detailsHeight
        notchCardHeightWithOutPaymentDetails = promoHeight + optionsHeight
        if (notchCardHeightWithPaymentDetails > display.height - topGap) {
            notchCardHeightWithPaymentDetails = display.height - topGap
        }
        if (notchCardHeightWithOutPaymentDetails > notchCardHeightWithPaymentDetails) {
            notchCardHeightWithOutPaymentDetails = notchCardHeightWithPaymentDetails
        }

        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(binding.parentContainer)
        behavior.peekHeight = notchCardHeightWithOutPaymentDetails + btnHeight
        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    binding.detailscontainer.visibility = VISIBLE
                    binding.childView.setPadding(0, 0, 0, btnHeight)
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    binding.detailscontainer.visibility = GONE
                    binding.childView.setPadding(0, 0, 0, btnHeight + topGap)
                }
            }
        })

        binding.childView.setPadding(0, 0, 0, btnHeight + topGap)
        bindingTemp.listview.adapter = ArrayAdapter<String>(
            this,
            R.layout.simple_list_item_1,
            getData()
        )
    }

    private fun getData(): List<String> {
        return listOf(
            "Payment option 1",
            "Payment option 2",
            "Payment option 3",
            "Payment option 4",
            "Payment option 5",
            "Payment option 6"
//            "Payment option 7",
//            "Payment option 8",
//            "Payment option 9",
//            "Payment option 10"
        )
    }
}