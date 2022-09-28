package dom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface DOM{
	public final class _Tag {
		public static final String _Root = "xs:schema";

		public static final String _ComplexType = "xs:complexType";
		
		public static final String _Sequence = "xs:sequence";
		
		public static final String _Child = "xs:element";
		
		public static final String _Label = "osd:label";
		
	}
	
	public final class _Attribute{
		public static final String _Name = "name";
		
		public static final String _Type = "type";
	}
	
	public final class _BasicType{
		public static final String _String = "xs:string";
		
		public static final String _Boolean = "xs:boolean";
		
		public static final String _Decimal = "xs:decimal";
	
		public static final String _Datetime = "xs:dateTime";
		
		public static final String _Integer = "xs:int";
		
		public static final String _Date = "xs:date";
		
		public static final String _Color = "osd:color";
		
		public static final List<String>getAllBasicTypes(){
			List<String>basicTypeList = new ArrayList<>();
			
			basicTypeList.addAll(Arrays.asList(_String, _Boolean, _Decimal, _Datetime, _Integer, _Date, _Color));;
			
			return basicTypeList;
		}
	}

}

