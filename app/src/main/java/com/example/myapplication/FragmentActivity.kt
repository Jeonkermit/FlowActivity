package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentActivity : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val frag = inflater.inflate(R.layout.activity_fragment, container, false)
        val num = arguments?.getString("num")
        val numInt = arguments?.getInt("num") ?: 0
        val randNum = (0..numInt!!.toInt())
        val JustFragText = frag.findViewById<TextView>(R.id.JustFragText)
        val FragNum = frag.findViewById<TextView>(R.id.FragNum)

        JustFragText.text = "Here is a random number between 0 to" + num
        FragNum.text = randNum.random().toString()

        return frag
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}