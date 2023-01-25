package com.example.testapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.utils.CardAdapter
import com.example.testapp.viewModel.CardViewModel


class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: CardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var latitude = ""
        var longitude = ""

        val button = view.findViewById<Button>(R.id.searchBin)
        val binEditText = view.findViewById<EditText>(R.id.editBin)
        val binText = view.findViewById<TextView>(R.id.textView)
        val schemeText = view.findViewById<TextView>(R.id.textViewScheme)
        val brandText = view.findViewById<TextView>(R.id.textViewBrand)
        val lengthText = view.findViewById<TextView>(R.id.textViewLength)
        val luhnText = view.findViewById<TextView>(R.id.textViewLuhn)
        val typeText = view.findViewById<TextView>(R.id.textViewType)
        val prepaidText = view.findViewById<TextView>(R.id.textViewPrepaid)
        val countryText = view.findViewById<TextView>(R.id.textViewCountry)
        val latLongText = view.findViewById<TextView>(R.id.textViewLatLong)
        val bankNameText = view.findViewById<TextView>(R.id.textViewBank)
        val bankSiteText = view.findViewById<TextView>(R.id.textViewBankSite)
        val bankPhoneText = view.findViewById<TextView>(R.id.textViewBankPhone)

        val cardInvisible = view.findViewById<ImageView>(R.id.cardShapeInvisible)

        val listResults = view.findViewById<RecyclerView>(R.id.listResults)
        val textViewZeroResult = view.findViewById<TextView>(R.id.textViewZeroResult)

        val cardAdapter = CardAdapter()
        with(listResults) {
            adapter = cardAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        bankPhoneText.setOnClickListener {
            if(bankPhoneText.text!="N/A"){
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:" + "${bankPhoneText.text}")
            startActivity(callIntent)}
            else{
                toast()
            }
        }

        latLongText.setOnClickListener {
            if(latLongText.text!="N/A N/A"){
            val mapIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:$latitude,$longitude")
            )
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)}
            else{
                toast()
            }
        }

        bankSiteText.setOnClickListener {
            if(bankSiteText.text!="N/A"){
            val urlIntent = Intent(Intent.ACTION_VIEW)
            urlIntent.data = Uri.parse("https://" + bankSiteText.text.toString())
            startActivity(urlIntent)}
            else{
                toast()
            }
        }

        button.setOnClickListener {
            viewModel.search(binEditText.text.toString())
        }

        viewModel.cardInfo.observe(viewLifecycleOwner) {
            schemeText.text = it?.scheme ?: "N/A"
            brandText.text = it?.brand ?: "N/A"
            lengthText.text = (it?.number?.length ?: "N/A").toString()

            if (it?.number?.luhn.toString() == "true") {
                luhnText.text = "Yes"
            } else {
                luhnText.text = "No"
            }

            typeText.text = it?.type ?: "N/A"

            if (it?.prepaid.toString() == "true") {
                prepaidText.text = "Yes"
            } else {
                prepaidText.text = "No"
            }

            countryText.text = (it?.country?.emoji ?: "N/A") +
                    (it?.country?.name ?: "N/A")

            latitude = it?.country?.latitude.toString()
            longitude = it?.country?.longitude.toString()

            latLongText.text =
                (it?.country?.latitude
                    ?: "N/A").toString() + " " + (it?.country?.longitude
                    ?: "N/A").toString()
            bankNameText.text =
                (it?.bank?.name ?: "N/A") + "," + (it?.bank?.city ?: "N/A")
            bankSiteText.text = it?.bank?.url ?: "N/A"
            bankPhoneText.text = it?.bank?.phone ?: "N/A"
            cardInvisible.isVisible = false
        }

        viewModel.message.observe(viewLifecycleOwner) {
            binText.text = it
        }

        viewModel.list.observe(viewLifecycleOwner) {
            cardAdapter.updateList(it)
            cardAdapter.notifyDataSetChanged()
            textViewZeroResult.isVisible = false
        }



    }


    private fun toast(){
        Toast.makeText(requireContext(),"No information available", Toast.LENGTH_SHORT).show()
    }


}