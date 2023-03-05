package ca.qc.cstj.tp01.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import ca.qc.cstj.tp01.R
import ca.qc.cstj.tp01.databinding.ActivityHomeBinding
import ca.qc.cstj.tp01.domain.models.Trader
import ca.qc.cstj.tp01.presentation.deliveries.DeliveriesActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private val viewModel : HomeViewModel by viewModels()
    //private val traderNameEmpty = getString(R.string.name_missing)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var trader:Trader = Trader()

        viewModel.mainUiState.onEach {
            when(it){
                HomeUIState.Empty -> Unit

                is HomeUIState.Success -> {
                    trader = it.trader
                    binding.edtName.setText(it.trader.name)
                    binding.incElements.txtElementEx.text = String.format("%.2f", it.trader.ewhyx)
                    binding.incElements.txtElementWu.text =String.format("%.2f", it.trader.wusnyx)
                    binding.incElements.txtElementI.text =String.format("%.2f", it.trader.iaspyx)
                    binding.incElements.txtElementSm.text =String.format("%.2f", it.trader.smiathil)
                    binding.incElements.txtElementVe.text =String.format("%.2f", it.trader.vathyx)
                }
                HomeUIState.DeleteSuccess -> {
                    Toast.makeText(this, getString(R.string.upload_consortium), Toast.LENGTH_LONG).show()
                }
            }
        }.launchIn(lifecycleScope)

        binding.btnOpen.setOnClickListener{
            if (binding.edtName.text.toString().isNotBlank()) {
                viewModel.saveName(binding.edtName.text.toString())
                startActivity(DeliveriesActivity.newIntent(this))
            } else  {
                Toast.makeText(this, getString(R.string.name_missing), Toast.LENGTH_LONG).show()
            }
        }

        binding.btnReload.setOnClickListener{
            if (binding.edtName.text.toString().isNotBlank()) {
                viewModel.rechargeElements(trader)
            } else  {
                Toast.makeText(this, getString(R.string.name_missing), Toast.LENGTH_LONG).show()
            }
        }

        binding.btnUplaod.setOnClickListener{
            if (binding.edtName.text.toString().isNotBlank()) {
                viewModel.upload()
            } else  {
                Toast.makeText(this, getString(R.string.name_missing), Toast.LENGTH_LONG).show()
            }
        }
    }
}