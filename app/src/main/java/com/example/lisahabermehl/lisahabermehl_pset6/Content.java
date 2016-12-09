//package com.example.lisahabermehl.lisahabermehl_pset6;
//
//import android.util.Log;
//import android.widget.Toast;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
// * Created by lisahabermehl on 09/12/16.
// */
//
//public class Content {
//
//    public static ArrayList<String> contactList = new ArrayList<>();
//    public static HashMap<String, String> contact = new HashMap<>();
//
//    public void Result(String result) {
//        if (result != null) {
//            try {
//                JSONObject jsonObj = new JSONObject(result);
//
//                // Getting JSON Array node
//                JSONArray contacts = jsonObj.getJSONArray("data");
//
//                // looping through All Contacts
//                for (int i = 0; i < contacts.length(); i++) {
//                    JSONObject c = contacts.getJSONObject(i);
//
//                    String id = c.getString("bitly_url");
//                    Log.d("BITLY URL", id);
//                    String name = c.getString("slug");
//                    Log.d("SLUG", name);
//                    String email = c.getString("content_url");
//                    Log.d("CONTENT_URL", email);
//                    String address = c.getString("embed_url");
//                    String gender = c.getString("id");
//
//                    // Phone node is JSON Object
//                    JSONObject phone = c.getJSONObject("images");
//                    JSONObject down = phone.getJSONObject("downsized");
//                    String mobile = down.getString("url");
//                    String home = down.getString("size");
//                    String office = down.getString("url");
//                    String width = down.getString("width");
//
//                    // tmp hash map for single contact
//                    HashMap<String, String> contact = new HashMap<>();
//
//                    int name_length = name.length();
//                    int space_here = 0;
//
//                    for(int j = 0; j < name_length; j++) {
//
//                        char character = name.charAt(j);
//
//                        int ascii_value = (int) character;
//
//                        if (character == '-') {
//                            char[] string_to_char = name.toCharArray();
//                            string_to_char[j] = ' ';
//                            space_here = j;
//                            name = String.valueOf(string_to_char);
//                            Log.d("- to space", name);
//                        }
//
//                        if (ascii_value < 97 && ascii_value > 122) {
//
//                            char[] string_to_char = name.toCharArray();
//
//                            for(int k = space_here; k < j; k++) {
//                                string_to_char[k] = ' ';
//                            }
//                            name = String.valueOf(string_to_char);
//                            Log.d("int to space", name);
//
//                        }
//                    }
//
//                    // adding each child node to HashMap key => value
//                    contactList.add(id);
//                    contactList.add(name);
//                    contactList.add(mobile);
//
//                    contact.put("bitly_url", id);
//                    contact.put("slug", name);
//                    contact.put("content_url", email);
//                    contact.put("url", mobile);
//
//                    // adding contact to contact list
////                        contactList.add(contact);
//                }
//            } catch (final JSONException e) {
//                Log.e(TAG, "Json parsing error: " + e.getMessage());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Json parsing error: " + e.getMessage(),
//                                Toast.LENGTH_LONG)
//                                .show();
//                    }
//                });
//
//            }
//    }
//}
//
//
//    // make the option to add the ITEM
//    private static void addItem(Item item) {
//        // add the whole thing to the arraylist
//        ITEMS.add(item);
//        // add the ID en the whole thing to the hashmap
//        ITEM_MAP.put(item.id, item);
//    }
//
//    // make the ITEM
//    public static class Item {
//        String id;
//        String name;
//        String mobile;
//
//        public Item(String id, String name, String mobile) {
//            // will use id from JSON array later
//            this.id = id;
//            // will be the SLUR
//            this.name = name;
//            // the URL to the GIF
//            this.mobile = mobile;
//        }
//
//        @Override
//        public String toString() {
//            return name;
//        }
//
//    }
//
//}
