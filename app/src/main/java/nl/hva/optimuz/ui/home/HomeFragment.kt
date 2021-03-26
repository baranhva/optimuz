package nl.hva.optimuz.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import nl.hva.optimuz.R
import nl.hva.optimuz.ui.questionnaire_recycler.QuestionnaireRecFragment


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val testBtn: Button = root.findViewById(R.id.testBtn)
        testBtn.setOnClickListener {
//            openQuestionnairePanel()
        }

        return root
    }

//    private fun openQuestionnairePanel(){
//        (activity as MainActivity).navigateToFragment(R.id.navigation_questionnaire)
//    }

    private fun openQuestionnairePanel() {
        val fragment = QuestionnaireRecFragment()
        val fragmentManager: FragmentManager = requireActivity().getSupportFragmentManager()
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(((view as ViewGroup).parent as View).id, fragment, "tag")
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}