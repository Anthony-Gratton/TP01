package ca.qc.cstj.tp01.presentation.deliveries

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import ca.qc.cstj.tp01.databinding.ActivityDeliveriesBinding
import ca.qc.cstj.tp01.presentation.deliveries.adapters.DeliveriesRecyclerViewAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class DeliveriesActivity : AppCompatActivity() {

    private val viewModel : DeliveriesViewModel by viewModels()
    private lateinit var binding : ActivityDeliveriesBinding

    private val deliveriesRecyclerViewAdapter = DeliveriesRecyclerViewAdapter(listOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliveriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO: Configurer le layoutManager (grid de 2 colonnes) et l'adapter du recyclerView
        binding.rcvDeliveries.layoutManager = GridLayoutManager(this, 1)
        binding.rcvDeliveries.adapter = deliveriesRecyclerViewAdapter



        // TODO : Traiter le changement d'état
        viewModel.deliveriesUiState.onEach {
            when(it) {
                DeliveriesUIState.Empty -> Unit
                is DeliveriesUIState.Success -> {
                    binding.txtMessage.text = "Welcome Back ${it.trader.name}"
                    deliveriesRecyclerViewAdapter.deliveries = it.deliveries
                    deliveriesRecyclerViewAdapter.notifyDataSetChanged()
                }
            }
        }.launchIn(lifecycleScope)

        binding.fabAddDelivery.setOnClickListener{

        }

/*        binding.fabAddNote.setOnClickListener {
            startActivity(NoteActivity.newIntent(this))
        }

        binding.fabSettings.setOnClickListener {
            startActivity(PreferencesActivity.newIntent(this))
        }*/
    }



    companion object {
        fun newIntent(context : Context) : Intent {
            return Intent(context, DeliveriesActivity::class.java)
        }
    }
}