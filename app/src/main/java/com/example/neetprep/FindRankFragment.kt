package com.example.neetprep

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager

class FindRankFragment: Fragment() {


    var viewPager: ViewPager? = null
    var card_count:TextView?= null
    var next_text:TextView?= null
    var next_btn:Button?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.find_rank_fragment,container,false)
        viewPager = view.findViewById(R.id.card_view_pager)
        card_count = view.findViewById(R.id.card_number)
        next_text = view.findViewById(R.id.next_text)
        next_btn = view.findViewById(R.id.next_btn)
        next_btn?.visibility = View.GONE
        next_text?.visibility = View.VISIBLE
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewPager?.adapter != null) return
        viewPager?.adapter =  CardAdapter(activity!!,getQuestionList())


        viewPager?.setOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val count =  position + 1
                card_count?.text = "$count / 24"
                if(position == 23) {
                    next_btn?.text = "SUBMIT"
                    next_btn?.visibility = View.VISIBLE
                    next_text?.visibility = View.GONE
                    next_btn?.setOnClickListener {
                        val intent = Intent(activity!!, RankResultActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    next_btn?.visibility = View.GONE
                    next_text?.visibility = View.VISIBLE
                }
            }

            override fun onPageSelected(position: Int) {

            }
        })
        next_text?.setOnClickListener { viewPager?.currentItem?.plus(1)?.let { it1 ->
            viewPager?.setCurrentItem(
                it1, true)
        }; }

    }

    fun getQuestionList(): List<String> {
        return listOf("","","","","","","","","","","","","","","","","","","","","","","","")
    }
}