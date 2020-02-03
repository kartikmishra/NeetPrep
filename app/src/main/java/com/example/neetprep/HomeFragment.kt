package com.example.neetprep

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class HomeFragment : Fragment() {

    var currentPage: Int = 0
    var timer: Timer? = null
    private val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed
    private val PERIOD_MS: Long = 3000 // time in milliseconds between successive task executions.

    var viewPager: ViewPager? = null

    var dynamicAdapter:DynamicAdapter? = null

    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view =  inflater.inflate(R.layout.home_fragment,container, false)
        viewPager = view.findViewById(R.id.news_view_pager)
        attachViewPager()

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_mid)
        val storyRecyclerView = view.findViewById<RecyclerView>(R.id.rv_stories)
        this.dynamicAdapter = DynamicAdapter(activity!!, getDynamicList())

        val layoutManager = GridLayoutManager(activity!!,2)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = dynamicAdapter

        val storyLayoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL,false)
        val storyAdapter = StoryAdapter(activity!!,getStoryList())
        storyRecyclerView.layoutManager = storyLayoutManager
        storyRecyclerView.setHasFixedSize(true)
        storyRecyclerView.adapter = storyAdapter
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dynamicAdapter?.onItemClick = {
            val fragment = FindRankFragment()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.addToBackStack("id")
            fragmentTransaction.commit()
        }

    }

    private fun attachViewPager() {
        if (viewPager?.adapter != null) return
        viewPager?.adapter =  SliderAdapter(activity!!,getImageList())


        val handler = Handler()
        val runnable = Runnable {
            if (currentPage == getImageList().size) {
                currentPage = 0
            }
            viewPager?.setCurrentItem(currentPage++, true)
        }

        timer = Timer() // This will create a new Thread

        timer!!.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(runnable)
            }
        }, DELAY_MS, PERIOD_MS)
    }

    private fun getImageList(): List<Uri> {
        return listOf(
            Uri.parse("https://quotefancy.com/media/wallpaper/3840x2160/36863-Malcolm-S-Forbes-Quote-The-purpose-of-education-is-to-replace-an.jpg"),
            Uri.parse("https://quotefancy.com/media/wallpaper/3840x2160/10317-Leonardo-da-Vinci-Quote-Learning-never-exhausts-the-mind.jpg"),
            Uri.parse("https://quotefancy.com/media/wallpaper/3840x2160/31289-Conrad-Hall-Quote-You-are-always-a-student-never-a-master-You-have.jpg"))
    }

    private fun getDynamicList() : List<DynamicModel> {
        return listOf(DynamicModel("KNOW YOUR RANK",
            "https://public-zypher.s3.ap-south-1.amazonaws.com/genius.png"),
            DynamicModel("START PREP",
                "https://public-zypher.s3.ap-south-1.amazonaws.com/lightsaber.png"))
    }

    private fun getStoryList() : List<StoryModel> {
        return  listOf(StoryModel("https://neetprepr.b-cdn.net/WhatsApp+Image+2019-06-17+at+5.13.16+PM.jpeg",
            "Tushar Aggarwal","State - Delhi","NEET 2019 Score - 660"),

            StoryModel("https://learner-users.s3.ap-south-1.amazonaws.com/Screenshot+from+2019-08-12+14-39-07.png",
                "Nitin Kumar Pandey","State - Delhi","NEET 2019 Score - 650"),

            StoryModel("https://learner-users.s3.ap-south-1.amazonaws.com/Screenshot+from+2019-08-12+14-44-13.png",
                "Manya Gupta","State - Delhi","NEET 2019 Score - 612")
        )
    }



}