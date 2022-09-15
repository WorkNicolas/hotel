package service;

import javax.swing.*;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class RoomServiceView extends JPanel {
	protected ArrayList<Amenity> content;
	/**
	 * Create the panel.
	 */
	public RoomServiceView(ArrayList<Amenity> content) {
		this.content = content;
		RoomServiceComponents();
	}
	
	private void RoomServiceComponents() {
		setMaximumSize(new Dimension(1920, 1080));
		setPreferredSize(new Dimension(1920, 1080));
		setLayout(new BorderLayout(0, 0));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new LineBorder(new Color(64, 64, 64), 4));
		titlePanel.setPreferredSize(new Dimension(1980, 50));
		add(titlePanel, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Hotel Food Menu & Necessities");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));
		titlePanel.add(lblTitle);
		
		JPanel foodPanel = new JPanel();
		foodPanel.setPreferredSize(new Dimension(800, 10));
		foodPanel.setBorder(new LineBorder(new Color(64, 64, 64), 4));
		add(foodPanel, BorderLayout.WEST);
		foodPanel.setLayout(new GridLayout(0, 2, 5, 5));
		
		JLabel lblBreakfast = new JLabel("Breakfast");
		lblBreakfast.setHorizontalAlignment(SwingConstants.CENTER);
		lblBreakfast.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBreakfast.setVerticalAlignment(SwingConstants.TOP);
		foodPanel.add(lblBreakfast);
		
		JLabel lblDessert = new JLabel("Dessert");
		lblDessert.setHorizontalAlignment(SwingConstants.CENTER);
		lblDessert.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDessert.setVerticalAlignment(SwingConstants.TOP);
		foodPanel.add(lblDessert);
		
		JLabel lblItems = new JLabel();
		
		JList<Amenity> breakfastList = createPart(Type.BREAKFAST, content, lblItems);
		breakfastList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		breakfastList.setBorder(new LineBorder(Color.GRAY, 2));
		foodPanel.add(new JScrollPane (breakfastList));
		
		JList<Amenity> dessertList = createPart(Type.DESSERT, content, lblItems);
		dessertList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dessertList.setBorder(new LineBorder(Color.GRAY, 2));
		add(new JScrollPane (dessertList));
		foodPanel.add(new JScrollPane (dessertList));
		
		JLabel lblLunchDinner = new JLabel("Lunch & Dinner");
		lblLunchDinner.setHorizontalAlignment(SwingConstants.CENTER);
		lblLunchDinner.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLunchDinner.setVerticalAlignment(SwingConstants.TOP);
		foodPanel.add(lblLunchDinner);
		
		JLabel lblBeverages = new JLabel("Beverages");
		lblBeverages.setHorizontalAlignment(SwingConstants.CENTER);
		lblBeverages.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBeverages.setVerticalAlignment(SwingConstants.TOP);
		foodPanel.add(lblBeverages);
		
		JList<Amenity> lunchDinnerList = createPart(Type.DISH, content, lblItems);
		lunchDinnerList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lunchDinnerList.setBorder(new LineBorder(Color.GRAY, 2));
		lunchDinnerList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		foodPanel.add(new JScrollPane (lunchDinnerList));
		
		JList<Amenity> beveragesList = createPart(Type.BEVERAGE, content, lblItems);
		beveragesList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		beveragesList.setBorder(new LineBorder(Color.GRAY, 2));
		foodPanel.add(new JScrollPane (beveragesList));
		
		JPanel necessitiesPanel = new JPanel();
		necessitiesPanel.setPreferredSize(new Dimension(800, 10));
		necessitiesPanel.setBorder(new LineBorder(new Color(64, 64, 64), 4));
		add(necessitiesPanel, BorderLayout.CENTER);
		necessitiesPanel.setLayout(new GridLayout(2, 1, 5, 5));
		
		JLabel lblHotelNecessities = new JLabel("Hotel Necessities");
		lblHotelNecessities.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotelNecessities.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JList<Amenity> hotelNecessitiesList = createPart(Type.THING, content, lblItems);
		hotelNecessitiesList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		hotelNecessitiesList.setBorder(new LineBorder(Color.GRAY, 2));
		hotelNecessitiesList.setVisibleRowCount(4);
		necessitiesPanel.add(lblHotelNecessities);
		necessitiesPanel.add(new JScrollPane (hotelNecessitiesList));
		
		JPanel orderPanel = new JPanel();
		orderPanel.setPreferredSize(new Dimension(1980, 250));
		orderPanel.setBorder(new LineBorder(new Color(64, 64, 64), 4));
		add(orderPanel, BorderLayout.SOUTH);
		orderPanel.setLayout(null);
		

		JLabel lblOrder = new JLabel("Order");
		lblOrder.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOrder.setBounds(27, 23, 310, 20);
		orderPanel.add(lblOrder);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(1224, 195, 116, 44);
		orderPanel.add(btnReturn);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(1098, 195, 116, 44);
		orderPanel.add(btnOrder);
		
		lblItems.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItems.setBounds(68, 54, 439, 44);
		orderPanel.add(lblItems);
	}

	public JList<Amenity> createPart(Type t, ArrayList<Amenity> amenities, JLabel label) {
		DefaultListModel<Amenity> model = new DefaultListModel<>();
		for (var a: content) {
			if (a.type.equals(t)) {
				model.addElement(a);
			}
		}
		JList<Amenity> list = new JList<>(model);
		list.getSelectionModel().addListSelectionListener(e -> {
			Amenity a = list.getSelectedValue();
			label.setText(a + " Price = " + a.getPrice());
		}); 

		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		return list;
	}
}
