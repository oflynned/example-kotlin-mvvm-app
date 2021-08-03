package com.syzible.search.detailview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.syzible.search.R
import com.syzible.search.databinding.FragmentSearchDetailViewBinding

class DetailViewFragment : Fragment() {

    private var _binding: FragmentSearchDetailViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchDetailViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_detail_view_to_search_results)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
