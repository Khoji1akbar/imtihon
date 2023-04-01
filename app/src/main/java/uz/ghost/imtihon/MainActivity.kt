package uz.ghost.imtihon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import uz.ghost.imtihon.databinding.ActivityMainBinding
import uz.ghost.imtihon.databinding.ItemAddBinding

class MainActivity : AppCompatActivity() {
    class MainActivity : AppCompatActivity() {
            private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
            private lateinit var myDbHelper: MyDbHelper
            private lateinit var myContactAdapter: MyConatctAdabter.MyContactAdapter
            private lateinit var list: ArrayList<Mycontact>
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(binding.root)

                binding.menu.setOnClickListener {
                    binding.container.open()
                }
                loadData()

                binding.myNav.setNavigationItemSelectedListener {
                    when(it.itemId){
                        R.layout.item_add->{
                            val dialog = AlertDialog.Builder(this@MainActivity).create()
                            val itemDialogBinding = ItemAddBinding.inflate(layoutInflater)
                            dialog.setView(itemDialogBinding.root)
                            dialog.show()

                            itemDialogBinding.btnSave.setOnClickListener {
                                val myContact = MYconatctt(
                                    itemDialogBinding.tvName.text.toString().trim(),
                                    itemDialogBinding.tvNumber.text.toString()
                                )
                                myDbHelper.addContact(myContact)
                                loadData()
                                dialog.cancel()
                                binding.container.close()
                            }
                        }
                    }
                    true}

                binding.menu.setOnClickListener {
                    val dialog = AlertDialog.Builder(this@MainActivity).create()
                    val itemDialogBinding = ItemAddBinding.inflate(layoutInflater)
                    dialog.setView(itemDialogBinding.root)
                    dialog.show()
                    itemDialogBinding.btnSave.setOnClickListener {
                        val myContact = MYconatctt(
                            itemDialogBinding.tvName.text.toString().trim(),
                            itemDialogBinding.tvNumber.text.toString()
                        )
                        myDbHelper.addContact(myContact)
                        loadData()
                        dialog.cancel()
                    }
                }
            }
            private fun loadData() {
                myDbHelper = MyDbHelper(this)
                list = ArrayList()
                list.addAll(myDbHelper.getAllContacts())
                myContactAdapter = MyConatctAdabter.MyContactAdapter(list, object :
                    MyConatctAdabter.RvAction {
                    override fun deleteContact(contact: MYconatctt, position: Int) {
                        myDbHelper.deleteContact(contact)
                        list.remove(Mycontact)
                        myContactAdapter.notifyItemRemoved(position)
                        Toast.makeText(this@MainActivity, "Delete", Toast.LENGTH_SHORT).show()
                    }
                    override fun updateContact(contact: MYconatctt, position: Int) {
                        val dialog = AlertDialog.Builder(this@MainActivity).create()
                        val itemDialogBinding = ItemAddBinding.inflate(layoutInflater)
                        itemDialogBinding.tvName.setText(contact.name)
                        itemDialogBinding.tvNumber.setText(contact.number)
                        itemDialogBinding.btnSave.setOnClickListener {
                            contact.name = itemDialogBinding.tvName.text.toString()
                            contact.number = itemDialogBinding.tvNumber.text.toString()
                            myDbHelper.upDateContact(contact)
                            list[position] = Mycontact
                            myContactAdapter.notifyItemChanged(position)
                            Toast.makeText(this@MainActivity, "Save", Toast.LENGTH_SHORT).show()
                            dialog.cancel()
                        }
                        dialog.setView(itemDialogBinding.root)
                        dialog.show()
                    }
                })
                binding.iv1.adapter = myContactAdapter
            }
        }
}