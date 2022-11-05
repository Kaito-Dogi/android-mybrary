package app.doggy.newmybrary.ui.book.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.doggy.newmybrary.R
import app.doggy.newmybrary.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {
  companion object {
    fun newInstance() = RegisterFragment()
  }

  private var _binding: FragmentRegisterBinding? = null
  private val binding: FragmentRegisterBinding
    get() = _binding!!

  private val viewModel: RegisterViewModel by viewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    _binding = FragmentRegisterBinding.bind(view)
    setUpButton()
  }

  private fun setUpButton() {
    binding.registerButton.setOnClickListener {
      viewModel.onRegisterButtonClick(
        // TODO: booksApiId, imageUrl が存在する場合を考える
        title = binding.titleEditText.text.toString(),
        author = binding.authorEditText.text.toString(),
        description = binding.descriptionEditText.text.toString(),
        totalPage = binding.totalPageEditText.text.toString(),
      )
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
