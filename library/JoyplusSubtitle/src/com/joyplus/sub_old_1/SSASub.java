package com.joyplus.sub_old_1;

import java.io.File;
import java.util.Iterator;

import com.joyplus.sub.SUBTYPE;
import com.joyplus.sub.SubURI;

public class SSASub extends JoyplusSub{

	public SSASub(SubURI uri) {
		super(uri);
		// TODO Auto-generated constructor stub
		this.mContentType = SubContentType.SUB_SSA;
		CheckUri();
	}
	private void CheckUri() {
		// TODO Auto-generated method stub
		JoyplusSubContentRestrictionFactory.getContentRestriction().checkUri(SubContentType.SUB_SSA, this.getUri().Uri);
	}
	private void CheckSize(byte[] Sub){
    	JoyplusSubContentRestrictionFactory.getContentRestriction().checkSubSize(0, Sub.length);
    }
	@Override
	public void parse(byte[] sub) {
		// TODO Auto-generated method stub
		if(this.getUri().SubType != SUBTYPE.NETWORK)return;
		CheckSize(sub);
		//don't support it now
	}

	@Override
	public void parseLocal() {
		// TODO Auto-generated method stub
		if(this.getUri().SubType != SUBTYPE.LOCAL)return;
		LocalSubParser parser = new LocalSubParser();
		TimedTextObject obd = parser.ParserFile(new File(this.getUri().Uri));
		if(obd != null && obd.captions!=null && obd.captions.size()>0){
			Iterator<Integer> iterator_2 = obd.captions.keySet().iterator(); 
			int index = 0;
			while (iterator_2.hasNext()) { 
				 Caption mCaption = obd.captions.get(iterator_2.next());
				 Element mElement = new Element();
				 mElement.setRank(++index);
				 mElement.setStartTime(new JoyplusSubTime(mCaption.start.mseconds));
				 mElement.setEndTime(new JoyplusSubTime(mCaption.end.mseconds));
				 this.elements.add(mElement);
			} 
		}
	}
}
