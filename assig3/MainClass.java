package assig3;

import java.sql.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.*;

import DataBase.ConnectionFactory;
import dao.*;
import bll.*;
import model.*;
import presentation.*;

public class MainClass {

	public static void main(String[] args) {
		View view = new View();
		Controller cont = new Controller(view);
	}
}
