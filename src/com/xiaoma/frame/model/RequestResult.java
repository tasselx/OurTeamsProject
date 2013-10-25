/*
 * FileName:  RequestResult.java
 * CopyRight:  Belong to  <XiaoMaGuo Technologies > own 
 * Description:  <description>
 * Modify By :  XiaoMaGuo ^_^ 
 * Modify Date:   2013-10-12
 * Follow Order No.:  <Follow Order No.>
 * Modify Order No.:  <Modify Order No.>
 * Modify Content:  <modify content >
 */
package com.xiaoma.frame.model;

/**
 * @TODO [The Class File Description]
 * @author XiaoMaGuo ^_^
 * @version [version-code, 2013-10-12]
 * @since [Product/module]
 */
public class RequestResult
{
    private String username = null;
    
    private String password = null;
    
    /**
     * Getters username
     * 
     * @return Return username
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * Setter username
     * 
     * @param Set Value username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     * Getters password
     * 
     * @return Return password
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     * Setter password
     * 
     * @param Set Value password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
}
