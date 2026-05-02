package com.example.aliya_blush.More

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aliya_blush.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        setupSocialLinks()

        return binding.root
    }

    private fun setupSocialLinks() {

        // Instagram
        binding.icInstagram.setOnClickListener {
            openUrl("https://www.instagram.com/aliyaa.rahmaaa?igsh=MXA2cHBrcjViNDE2NQ==")
        }

        // GitHub
        binding.icGithub.setOnClickListener {
            openUrl("https://github.com/alyrhmaa/prak_mobile.git")
        }

        // LinkedIn
        binding.icLinkedin.setOnClickListener {
            openUrl("https://www.linkedin.com/in/aliya-rahma-5a22a1260")
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}