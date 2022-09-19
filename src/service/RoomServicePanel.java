package service;
import javax.swing.*;

import javax.swing.border.LineBorder;

import java.awt.*;
import java.util.ArrayList;

public class RoomServicePanel extends JPanel {
	private JTextField txtNumOrder;
	public ArrayList<Amenity> content;
	protected JButton addOrder;
	protected JButton commitOrder;
	protected JButton btnReturn;
	protected ArrayList<JList<Amenity>> lists = new ArrayList<>();
	/**
	 * Create the panel.
	 */
	public RoomServicePanel() {
		content = new ArrayList<>();
		RoomServiceComponents();
	}
	
	public void RoomServiceComponents() {
		removeAll();
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
		
		JLabel lblItems = new JLabel(); //adjusted
		
		//might delete this jlist items, just use it to show how the jlist works for order panel
		
		var breakfastList = createPart(Type.BREAKFAST, content, lblBreakfast, ListSelectionModel.SINGLE_SELECTION);
		foodPanel.add(new JScrollPane (breakfastList));
		
		//might delete this jlist items, just use it to show how the jlist works for order panel
		
		var dessertList = createPart(Type.DESSERT, content, lblDessert, ListSelectionModel.SINGLE_SELECTION);
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
		
		//might delete this jlist items, just use it to show how the jlist works for order panel
		
		var lunchDinnerList = createPart(Type.DISH, content, lblLunchDinner, ListSelectionModel.SINGLE_SELECTION);
		lunchDinnerList.setBorder(new LineBorder(Color.GRAY, 2));
		foodPanel.add(new JScrollPane (lunchDinnerList));
		
		//might delete this jlist items, just use it to show how the jlist works for order panel
		
		var beveragesList = createPart(Type.BEVERAGE, content, lblBeverages, ListSelectionModel.SINGLE_SELECTION);
		foodPanel.add(new JScrollPane (beveragesList));
		
		JPanel necessitiesPanel = new JPanel();
		necessitiesPanel.setPreferredSize(new Dimension(800, 10));
		necessitiesPanel.setBorder(new LineBorder(new Color(64, 64, 64), 4));
		add(necessitiesPanel, BorderLayout.CENTER);
		necessitiesPanel.setLayout(new GridLayout(2, 1, 5, 5));
		
		JLabel lblHotelNecessities = new JLabel("Hotel Necessities");
		lblHotelNecessities.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotelNecessities.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		//might delete this jlist items, just use it to show how the jlist works for order panel
		
		var hotelNecessitiesList = createPart(Type.THING, content,lblHotelNecessities, ListSelectionModel.SINGLE_SELECTION);
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
		lblOrder.setBounds(130, 23, 310, 20); //adjusted
		orderPanel.add(lblOrder);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(1224, 195, 116, 44);
		orderPanel.add(btnReturn);
		
		commitOrder = new JButton("Order");
		commitOrder.setBounds(1098, 195, 116, 44);
		orderPanel.add(commitOrder);
		
		lblItems.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItems.setBounds(558, 67, 239, 44);
		orderPanel.add(lblItems);
		
		// new added -----------------------------------------------------
		
		JList<Amenity> orderList = new JList<>();
		DefaultListModel<Amenity> orderListModel = new DefaultListModel<>();
		JScrollPane orderListScrollPane = new JScrollPane(orderList);
		orderListScrollPane.setBorder(new LineBorder(Color.BLACK, 2));
		orderListScrollPane.setBounds(130, 54, 359, 141); //adjusted
		orderList.setVisibleRowCount(5);
		
		orderPanel.add(orderListScrollPane);
		
		addOrder = new JButton("Add Order");
		addOrder.setBounds(558, 162, 95, 33);
		orderPanel.add(addOrder);
		
		//add selected items from other list to order list
		lists.add(breakfastList);
		lists.add(dessertList);
		lists.add(lunchDinnerList);
		lists.add(beveragesList);
		lists.add(hotelNecessitiesList);
		//DONT INCLUDE orderList
		addOrder.addActionListener(e-> {
			for (var list: lists) {
				orderListModel
				.addElement(list.getSelectedValue());
				list.clearSelection();
			}
			orderList.setModel(orderListModel);
		});
		
		//text field for the number of a selected item
		
		txtNumOrder = new JTextField();
		txtNumOrder.setBounds(681, 122, 116, 23);
		orderPanel.add(txtNumOrder);
		txtNumOrder.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount (Order): ");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAmount.setBounds(558, 123, 113, 19);
		orderPanel.add(lblAmount);
		
		JButton btnRemoveOrder = new JButton("Remove Order");
		btnRemoveOrder.setBounds(681, 162, 116, 33);
		orderPanel.add(btnRemoveOrder);
		
		//removing selected order from the order list
		
		btnRemoveOrder.addActionListener(e -> {
				int i = orderList.getSelectedIndex();
				orderListModel.removeElementAt(i);
		});
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotal.setBounds(130, 207, 54, 19);
		orderPanel.add(lblTotal);
		
		//label for the total order
		
		JLabel lblTotalOrder = new JLabel("");
		lblTotalOrder.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalOrder.setBounds(178, 207, 116, 20);
		orderPanel.add(lblTotalOrder);
	}

	/**
	 * Create the panel.
	 */
	public RoomServicePanel(ArrayList<Amenity> content) {
		this.content = content;
		RoomServiceComponents();
	}

	public JList<Amenity> createPart(Type t, ArrayList<Amenity> amenities, JLabel label, int selectionMode) {
		DefaultListModel<Amenity> model = new DefaultListModel<>();
		for (var a: content) {
			if (a.type.equals(t)) {
				model.addElement(a);
			}
		}
		JList<Amenity> list = new JList<>(model);
		list.getSelectionModel().addListSelectionListener(e -> {
			Amenity a = list.getSelectedValue();
			if (a == null)
				return;
			label.setText(a + " Price = " + a.getPrice());
		}); 

		list.setSelectionMode(selectionMode);
		return list;
	}
}