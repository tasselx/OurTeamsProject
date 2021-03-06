package com.joyplus.sub;

import java.io.File;
import java.util.Iterator;

public class STLSub extends JoyplusSub{

	public STLSub(SubURI uri) {
		super(uri);
		// TODO Auto-generated constructor stub
		this.mContentType = SubContentType.SUB_STL;
		CheckUri();
	}
	private void CheckUri() {
		// TODO Auto-generated method stub
		JoyplusSubContentRestrictionFactory.getContentRestriction().checkUri(SubContentType.SUB_STL, this.getUri());
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
		TimedTextObject obd = parser.ParserFile(new File(this.getUri().getUrl()));
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

