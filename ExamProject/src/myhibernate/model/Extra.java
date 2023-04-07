package myhibernate.model;

public class Extra {
	String index;
	String likes;
	String dislikes;
	String recommend;
	String user;
	
	int size;
	int[] indexArray;
	String[] likesArray;
	String[] dislikesArray;
	String[] recommendArray;
	String[] userArray;
	
	public int getSize()	{
		return size;
	}
	public int[] getIndexArray()	{
		return indexArray;
	}
	public String[]	getLikesArray()	{
		return likesArray;
	}
	public String[]	getDislikesArray()	{
		return dislikesArray;
	}
	public String[]	getRecommendArray()	{
		return recommendArray;
	}
	public String[]	getUserArray()	{
		return userArray;
	}
	public Extra(String str)	{
		int in = str.indexOf("&", 0);
		System.out.println("str ="+str);
		if(in == -1)	{
			return;
		}
		index = str.substring(0, in);
		int newindex = str.indexOf("&", in+1);
		likes = str.substring(in+1,newindex);
		in = str.indexOf("&", newindex+1);
		dislikes = str.substring(newindex+1,in);
		
		newindex = str.indexOf("&", in+1);
		recommend=str.substring(in+1,newindex);
		user = str.substring(newindex+1,str.length());
		in = 0;
		newindex = 0;
		size = 0;
		while(true)	{
			in = index.indexOf("|",in+1);
			//System.out.println(in+"**");
			if(index.length() == 1)	{
				size++;
				break;
			}
			if(in == -1) {
				break;
			}
			else if(size == 0)	{
				size = 2;
			}
			else {
				size++;
			}
		}
		indexArray=new int[size];
		likesArray=new String[size];
		dislikesArray=new String[size];
		recommendArray=new String[size];
		userArray = new String[size];
		in =0;
		for(int i = 0;i<size;i++)	{
			int temp = in;
			in = index.indexOf("|",in+1);
			if(temp == 0 && in != -1)	{
				indexArray[i] = Integer.parseInt(index.substring(temp, in));
			}
			else if(temp == 0 && in == -1)	{
				
				indexArray[i] = Integer.parseInt(index.substring(temp, index.length()));
			}
			else if(in != -1)	{
				indexArray[i] = Integer.parseInt(index.substring(temp+1, in));
			}
			else {
				indexArray[i] = Integer.parseInt(index.substring(temp+1, index.length()));
			}
		}
		in = 0;
		for(int i = 0;i<size;i++)	{
			int temp = in;
			in = likes.indexOf("|",in+1);
			if(temp == 0 && in != -1)	{
				likesArray[i] = likes.substring(temp, in);
			}
			else if(temp == 0 && in == -1)	{
				
				likesArray[i] = likes.substring(temp, likes.length());
			}
			else if(in != -1)	{
				likesArray[i] = likes.substring(temp+1, in);
			}
			else {
				likesArray[i] = likes.substring(temp+1, likes.length());
			}
		}
		in = 0;
		for(int i = 0;i<size;i++)	{
			int temp = in;
			in = dislikes.indexOf("|",in+1);
			if(temp == 0 && in != -1)	{
				dislikesArray[i] = dislikes.substring(temp, in);
				
			}
			else if(temp == 0 && in == -1)	{
				
				dislikesArray[i] = dislikes.substring(temp, dislikes.length());
			}
			else if(in != -1)	{
				dislikesArray[i] = dislikes.substring(temp+1, in);
			}
			else {
				dislikesArray[i] = dislikes.substring(temp+1, dislikes.length());
				
			}
		}
		in = 0;
		for(int i = 0;i<size;i++)	{
			int temp = in;
			in = recommend.indexOf("|",in+1);
			if(temp == 0 && in != -1)	{
				recommendArray[i] =recommend.substring(temp, in);
			}
			else if(temp == 0 && in == -1)	{
				
				recommendArray[i] =recommend.substring(temp, recommend.length());
			}
			else if(in != -1)	{
				recommendArray[i] =recommend.substring(temp+1, in);
			}
			else {
				recommendArray[i] =recommend.substring(temp+1,recommend.length());
				
			}
		}
		in = 0;
		for(int i = 0;i<size;i++)	{
			int temp = in;
			in = user.indexOf("|",in+1);
			if(temp == 0 && in != -1)	{
				userArray[i] =user.substring(temp, in);
			}
			else if(temp == 0 && in == -1)	{
				
				userArray[i] =user.substring(temp, user.length());
			}
			else if(in != -1)	{
				userArray[i] =user.substring(temp+1, in);
			}
			else {
				userArray[i] =user.substring(temp+1,user.length());
				
			}
		}
	}
	public int isEmpty()	{
		if(index.equals("") )	{
			return -1;
		}
		return 0;
	}
	public Extra() {
		size = 0;
		this.index = new String();
		this.likes = new String();
		this.dislikes = new String();
		this.recommend = new String();
		this.user = new String();
	}
	public Extra(String index,String likes,String dislikes,String recommend,String user) {
		size = 1;
		this.index = index;
		this.likes = likes;
		this.dislikes = dislikes;
		this.recommend = recommend;
	}
	public void Add(String index,String likes,String dislikes,String recommend,String user) {
		if(size == 0)	{
			size = 1;
			this.index = index;
			this.likes = likes;
			this.dislikes = dislikes;
			this.recommend = recommend;
			this.user = user;
		}
		else	{
			size = size +1;
			this.index = this.index +"|"+ index;
			this.likes = this.likes+"|"+likes;
			this.dislikes =this.dislikes +"|"+dislikes;
			this.recommend = this.recommend+"|"+recommend;
			this.user = this.user+"|"+user;
		}
	}
	public String toString()	{
		String str = "";
		str = index+"&"+likes+"&"+dislikes+"&"+recommend+"&"+user;
		return str;
	}
	public String arrayToString()	{
		String str = "";
		for(int i = 0;i<size;i++)	{
			if(i == size -1)	{
				str = str + indexArray[i]+"&";
			}
			else	{
				str = str + + indexArray[i]+"|";
			}
		}
		for(int i = 0;i<size;i++)	{
			if(i == size -1)	{
				str = str + likesArray[i]+"&";
			}
			else	{
				str = str + likesArray[i]+"|";
			}
		}
		for(int i = 0;i<size;i++)	{
			if(i == size -1)	{
				str = str + dislikesArray[i]+"&";
			}
			else	{
				str = str +  dislikesArray[i]+"|";
			}
		}
		for(int i = 0;i<size;i++)	{
			if(i == size -1)	{
				str = str + recommendArray[i]+"&";
			}
			else	{
				str = str +  recommendArray[i]+"|";
			}
		}
		return str;
	}
}
