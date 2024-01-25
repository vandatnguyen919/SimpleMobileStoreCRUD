/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workshop.dao;

import com.workshop.dto.Cart;
import com.workshop.dto.CartDetails;
import com.workshop.dto.Mobile;
import com.workshop.dto.User;
import com.workshop.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HIEU
 */
public class DAO {

    /**
     * Return the user that has logged in successfully, otherwise return null.
     *
     * @param username
     * @param password
     * @return the user or null
     */
    public static User login(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        User user = null;
        String sql = "select [userID], [fullName], [role]"
                + "   from [User]"
                + "   where [userID] = ? and [password] = ?";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    user = new User();

                    user.setUserID(rs.getString("userID"));
                    user.setFullName(rs.getString("fullName"));
                    user.setRole(rs.getInt("role"));
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
        return user;
    }

    /**
     * Return the user that has signed up successfully, otherwise return null.
     * Automatically, assigning 0 for role (0: user, 1: manager, 2: staff)
     *
     * @param username
     * @param password
     * @param fullName
     * @return the user or null
     */
    public static User signup(String username, String password, String fullName) {
        if (username == null || password == null || fullName == null) {
            return null;
        }
        User user = null;
        String sql = "insert into [User](userID, password, fullName, role)"
                + "   values(?, ?, ?, 0)";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullName);

            if (ps.executeUpdate() > 0) {
                user = new User();
                user.setUserID(username);
                user.setFullName(fullName);
                user.setRole(0);
            }
        } catch (Exception e) {
        }
        return user;
    }

    /**
     * Return the list of all mobiles.
     *
     * @return the list of mobiles
     */
    public static List<Mobile> getMobiles() {
        List<Mobile> list = new ArrayList<>();
        String sql = "select * from [Mobile]";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Mobile mobile = new Mobile();

                    mobile.setMobileID(rs.getString("mobileID"));
                    mobile.setName(rs.getString("name"));
                    mobile.setPrice(rs.getFloat("price"));
                    mobile.setQuantity(rs.getInt("quantity"));
                    mobile.setYearOfProduction(rs.getInt("yearOfProduction"));
                    mobile.setDescription(rs.getString("description"));
                    mobile.setNotSale(rs.getByte("notSale"));
                    mobile.setImagePath(rs.getString("imagePath"));

                    list.add(mobile);
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * Return the list of mobiles with price in range [minPrice, maxPrice]. If
     * minPrice is equal to -1, return the list of mobiles that less than or
     * equal to maxPrice. If maxPrice is equal to -1, return the list of mobiles
     * that greater than or equal to minPrice. Or both are equal to -1, return
     * the list of all mobiles.
     *
     * @param minPrice
     * @param maxPrice
     * @return the list of mobiles with that condition
     */
    public static List<Mobile> getMobiles(float minPrice, float maxPrice) {
        List<Mobile> list = new ArrayList<>();
        String sql = "";
        if (minPrice == -1 && maxPrice > -1) {
            sql = "select * from [Mobile] where price <= ?";
        } else if (minPrice > -1 && maxPrice == -1) {
            sql = "select * from [Mobile] where price >= ?";
        } else if (minPrice > -1 && maxPrice > -1) {
            sql = "select * from [Mobile] where ? <= price AND price <= ?";
        } else if (minPrice == -1 && maxPrice == -1) {
            sql = "select * from [Mobile]";
        }
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            if (minPrice == -1 && maxPrice > -1) {
                ps.setFloat(1, maxPrice);
            } else if (minPrice > -1 && maxPrice == -1) {
                ps.setFloat(1, minPrice);
            } else if (minPrice > -1 && maxPrice > -1) {
                ps.setFloat(1, minPrice);
                ps.setFloat(2, maxPrice);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Mobile mobile = new Mobile();

                    mobile.setMobileID(rs.getString("mobileID"));
                    mobile.setName(rs.getString("name"));
                    mobile.setPrice(rs.getFloat("price"));
                    mobile.setQuantity(rs.getInt("quantity"));
                    mobile.setYearOfProduction(rs.getInt("yearOfProduction"));
                    mobile.setDescription(rs.getString("description"));
                    mobile.setNotSale(rs.getByte("notSale"));
                    mobile.setImagePath(rs.getString("imagePath"));

                    list.add(mobile);
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * Return the list of mobiles with ID <em>OR</em> name.
     *
     * @param mobileID
     * @param name
     * @return the list of mobiles with that condition
     */
    public static List<Mobile> getMobiles(String mobileID, String name) {
        if (mobileID == null || name == null) {
            return new ArrayList<>();
        }
        List<Mobile> list = new ArrayList<>();
        String sql = "select * from [Mobile] where mobileID LIKE ? OR name LIKE ?";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, '%' + mobileID + '%');
            ps.setString(2, '%' + name + '%');
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Mobile mobile = new Mobile();

                    mobile.setMobileID(rs.getString("mobileID"));
                    mobile.setName(rs.getString("name"));
                    mobile.setPrice(rs.getFloat("price"));
                    mobile.setQuantity(rs.getInt("quantity"));
                    mobile.setYearOfProduction(rs.getInt("yearOfProduction"));
                    mobile.setDescription(rs.getString("description"));
                    mobile.setNotSale(rs.getByte("notSale"));
                    mobile.setImagePath(rs.getString("imagePath"));

                    list.add(mobile);
                }
            } catch (Exception e) {
            }

        } catch (Exception e) {
        }
        return list;
    }

    /**
     * Add a new mobile to the database.
     *
     * @param mobile
     * @return true if added successfully, otherwise false
     */
    public static boolean addMobile(Mobile mobile) {
        if (mobile == null) {
            return false;
        }
        boolean ok = false;
        String sql = "insert into [Mobile] values (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mobile.getMobileID());
            ps.setString(2, mobile.getName());
            ps.setFloat(3, mobile.getPrice());
            ps.setInt(4, mobile.getQuantity());
            ps.setInt(5, mobile.getYearOfProduction());
            ps.setString(6, mobile.getDescription());
            ps.setByte(7, mobile.getNotSale());
            ps.setString(8, mobile.getImagePath());
            ok = ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return ok;
    }

    /**
     * Delete a mobile in the database.
     *
     * @param mobileID
     * @return deleted mobile if deleted successfully, otherwise null
     */
    public static boolean deleteMobile(String mobileID) {
        if (mobileID == null) {
            return false;
        }
        boolean ok = false;
        String sql = "delete from [Mobile] where mobileID = ?";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mobileID);
            ok = ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return ok;
    }

    /**
     * Update information of a mobile in the database.
     *
     * @param mobile
     * @return true if updated successfully, otherwise false
     */
    public static boolean updateMobile(Mobile mobile) {
        if (mobile == null) {
            return false;
        }
        String sql = "update [Mobile]"
                + "   set name = ?, price = ?, quantity = ?, yearOfProduction = ?, description = ?, notSale = ?, imagePath = ?"
                + "   where mobileID = ?";
        boolean ok = false;
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mobile.getName());
            ps.setFloat(2, mobile.getPrice());
            ps.setInt(3, mobile.getQuantity());
            ps.setInt(4, mobile.getYearOfProduction());
            ps.setString(5, mobile.getDescription());
            ps.setByte(6, mobile.getNotSale());
            ps.setString(7, mobile.getImagePath());
            ps.setString(8, mobile.getMobileID());
            ok = ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return ok;
    }

    /**
     * Add a mobile to the user's cart.
     *
     * @param userID
     * @param mobileID
     * @return true if added successfully, otherwise false
     */
    public static boolean addToCart(String userID, String mobileID) {
        if (userID == null || mobileID == null) {
            return false;
        }
        boolean ok = false;
        String cartID = userID + "cart";
        String sql = "insert into [Cart](cartID, userID, totalQuantity, totalPrice) values "
                + " (?, ?, 0, 0)";
        String sql2 = "insert into [CartDetails](cartID, mobileID, totalQuantity, totalPrice) values "
                + " (?, ?, 1, 0)";
        String sql3 = "update [CartDetails]"
                + " set totalQuantity = totalQuantity + 1"
                + " where cartID = ? and mobileID = ?";
        try (Connection con = DBUtil.getConnection()) {
            // cart does not exist in Cart table
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, cartID);
                ps.setString(2, userID);
                ps.executeUpdate();
            } catch (SQLException e) {
            }
            // add a new mobile to cart
            try (PreparedStatement ps2 = con.prepareStatement(sql2)) {
                ps2.setString(1, cartID);
                ps2.setString(2, mobileID);
                ok = ps2.executeUpdate() > 0;
            } catch (Exception e) {
                // update the quantity of the added mobile, if user clicked Add to cart button again 
                try (PreparedStatement ps3 = con.prepareStatement(sql3)) {
                    ps3.setString(1, cartID);
                    ps3.setString(2, mobileID);
                    ok = ps3.executeUpdate() > 0;
                } catch (Exception ee) {
                }
            }
        } catch (Exception e) {
        }

        return ok;
    }

    /**
     * Delete a mobile from the user's cart.
     *
     * @param userID
     * @param mobileID
     * @return true if deleted successfully, otherwise false
     */
    public static boolean deleteFromCart(String userID, String mobileID) {
        if (userID == null || mobileID == null) {
            return false;
        }
        boolean ok = false;
        String cartID = userID + "cart";
        String sql = "delete from CartDetails where cartID = ? and mobileID = ?";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cartID);
            ps.setString(2, mobileID);
            ok = ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return ok;
    }

    /**
     * Increase the quantity of a product by 1.
     *
     * @param userID
     * @param mobileID
     * @return true if quantity of a product added by 1 successfully, otherwise
     * false.
     */
    public static boolean increaseQuantityInCartDetailsByOne(String userID, String mobileID) {
        if (userID == null || mobileID == null) {
            return false;
        }
        boolean ok = false;
        String cartID = userID + "cart";
        String sql = "update CartDetails"
                + " set totalQuantity = totalQuantity + 1"
                + " where cartID = ? and mobileID = ?";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cartID);
            ps.setString(2, mobileID);
            ok = ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return ok;
    }

    /**
     * Decrease the quantity of a product by 1.
     *
     * @param userID
     * @param mobileID
     * @return true if quantity of a product reduced by 1 successfully,
     * otherwise false.
     */
    public static boolean decreaseQuantityInCartDetailsByOne(String userID, String mobileID) {
        if (userID == null || mobileID == null) {
            return false;
        }
        boolean ok = false;
        String cartID = userID + "cart";
        String sql = "update CartDetails"
                + " set totalQuantity = totalQuantity - 1"
                + " where cartID = ? and mobileID = ?";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cartID);
            ps.setString(2, mobileID);
            ok = ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return ok;
    }

    /**
     * Return the list of cart details of user.
     *
     * @param userID
     * @return the list of all mobiles in the user's cart, if cart is not found,
     * returns null;
     */
    public static List<CartDetails> getCartDetails(String userID) {
        if (userID == null) {
            return new ArrayList<>();
        }
        List<CartDetails> list = new ArrayList<>();
        String cartID = userID + "cart";
        String sql = "select cd.cartID, cd.mobileID, cd.totalQuantity, cd.totalPrice, m.name, m.price, m.imagePath\n"
                + " from CartDetails cd, Mobile m\n"
                + " where cd.mobileID = m.mobileID and cd.cartID = ? ";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cartID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CartDetails cd = new CartDetails();
                    cd.setCartID(rs.getString("cartID"));
                    cd.setMobileID(rs.getString("mobileID"));
                    cd.setTotalQuantity(rs.getInt("totalQuantity"));
                    cd.setTotalPrice(rs.getFloat("totalPrice"));
                    cd.setName(rs.getString("name"));
                    cd.setPrice(rs.getString("price"));
                    cd.setImagePath(rs.getString("imagePath"));

                    list.add(cd);
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * Return user's cart in the database.
     *
     * @param userID
     * @return the user's cart. If not found, return null
     */
    public static Cart getCart(String userID) {
        if (userID == null) {
            return null;
        }
        Cart cart = null;
        String cartID = userID + "cart";
        String sql = "select * from Cart where cartID = ?";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cartID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cart = new Cart();

                    cart.setCartID(rs.getString("cartID"));
                    cart.setUserID(rs.getString("userID"));
                    cart.setTotalQuantity(rs.getInt("totalQuantity"));
                    cart.setTotalPrice(rs.getFloat("totalPrice"));
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
        return cart;
    }

}
