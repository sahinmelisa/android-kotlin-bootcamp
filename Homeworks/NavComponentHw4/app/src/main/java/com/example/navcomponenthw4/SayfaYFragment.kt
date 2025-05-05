package com.example.navcomponenthw4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navcomponenthw4.databinding.FragmentSayfaYBinding
import androidx.navigation.Navigation
import androidx.activity.OnBackPressedCallback
import android.util.Log

class SayfaYFragment : Fragment() {
    private lateinit var binding : FragmentSayfaYBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSayfaYBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val geriTusu = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.e("Sayfa Y", "Geri tuşuna basıldı, anasayfaya dönülüyor")
                Navigation.findNavController(requireView()).navigate(R.id.anasayfaGecis)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, geriTusu)
    }
}





