package com.example.userssharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userssharedpreferences.databinding.ActivityMainBinding
import com.google.android.gms.common.internal.Objects
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), UserAdapter.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /********************* SHARED_PREFERENCES *********************/

        val preferences = getPreferences(Context.MODE_PRIVATE)

        val isFirstTime = preferences.getBoolean(getString(R.string.shared_preferences_first_time), true)
        Log.i("SP", "${getString(R.string.shared_preferences_first_time)} = $isFirstTime")
        Log.i("SP", "${getString(R.string.sp_username)} = ${preferences.getString(getString(R.string.sp_username),"NA")}")

        if (isFirstTime) {
            val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)
            MaterialAlertDialogBuilder(this)
                .setView(dialogView)
                .setCancelable(false)
                .setTitle(R.string.user_register)
                .setPositiveButton(R.string.confirm_first_user_register, { dialogInterface, i ->
                    val username = dialogView.findViewById<TextInputEditText>(R.id.etUserRegisterName).text.toString()
                    with (preferences.edit()) {
                        putBoolean(getString(R.string.shared_preferences_first_time), false)
                        putString(getString(R.string.sp_username), username)
                            .apply()
                    }
                    Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show()
                })
                /*.setNegativeButton("Cancelar", null)*/
                .setNeutralButton("Invitado", null)
                .show()
        } else {
            val username = preferences.getString(getString(R.string.sp_username), getString(R.string.hint_username))
            Toast.makeText(this, "Bienvenido $username", Toast.LENGTH_SHORT).show()
        }

        /********************* ADAPTER *********************/

        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.rcRecycler.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    private fun getUsers(): MutableList<User> {
        val users = mutableListOf<User>()

        val pedro = User(1, "Pedro", "Orós", "https://static3.elcorreo.com/www/multimedia/202010/13/media/mm-famosos-biden-trump/1-clint-eastwood-afp.jpg")
        val berta = User(2, "Berta", "Sebastián", "https://cadenaser00.epimg.net/ser/imagenes/2018/08/06/television/1533549037_219335_1533549123_noticia_normal.jpg")
        val isabel = User(3, "Isabel", "Orós", "https://imag.malavida.com/mvimgbig/download-fs/aventuras-de-disney-frozen-24925-0.jpg")
        val vieux = User(4, "Pont", "Vieux", "https://d2lcsjo4hzzyvz.cloudfront.net/blog/wp-content/uploads/2020/03/Jim-Carrey-es-un-famoso-con-depresio%CC%81n-jpg.jpg")
        val ceferino = User(5, "Ceferino", "Palanca", "https://static.abc.es/media/estilo/2016/02/23/vin--510x287.jpg")

        users.add(pedro)
        users.add(berta)
        users.add(isabel)
        users.add(vieux)
        users.add(ceferino)
        users.add(pedro)
        users.add(berta)
        users.add(isabel)
        users.add(vieux)
        users.add(ceferino)
        users.add(pedro)
        users.add(berta)
        users.add(isabel)
        users.add(vieux)
        users.add(ceferino)
        users.add(pedro)
        users.add(berta)
        users.add(isabel)
        users.add(vieux)
        users.add(ceferino)

        return users
    }

    override fun onClick(user: User, position: Int) {
        Toast.makeText(this, "Describiendo a: $position -> ${user.name}", Toast.LENGTH_SHORT).show()
    }
}