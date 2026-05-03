/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1.system;

/**
 *
 * @author Delmark Lusterio
 */
       public class Performance {
        private String title;
        private boolean checked;

        public Performance(String title, boolean checked1) {
            this.title = title;
            this.checked = checked1;
        }

        public String getTitle() {
            return title;
        }
        public boolean isChecked() {
                return checked;
            }
    }