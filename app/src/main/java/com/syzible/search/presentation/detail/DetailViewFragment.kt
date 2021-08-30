package com.syzible.search.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.syzible.search.databinding.FragmentSearchDetailViewBinding
import com.syzible.search.type.DrinkCategory

class DetailViewFragment : Fragment() {

    private var _binding: FragmentSearchDetailViewBinding? = null
    private val binding get() = _binding!!

    private val args: DetailViewFragmentArgs by navArgs()

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
            findNavController().popBackStack()
        }

        binding.name.text = args.name
        binding.abv.text = args.abv
        binding.category.text = DrinkCategory.BEER.name

        if (args.styles.isEmpty()) {
            binding.styles.visibility = GONE
        } else {
            binding.styles.text = args.styles.joinToString(", ")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
