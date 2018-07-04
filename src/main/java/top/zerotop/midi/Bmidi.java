package top.zerotop.midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/**
 * 使用预定曲调生成midi音乐
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年7月4日下午6:39:53
 */
public class Bmidi {
	
	//note[]
	int [] sqarr = {
			 58, 57, 60, 59,65,64,58,57,63,62,56,55,60,64,62,60,59,57,55,53,55,57,59,62,60,59,
			 57,58,60,62,60,58,60,62,63,62,54,55,59,60,52,53,57,58,52,58,57,
			 58,60,62,59,60,55,53,57,58,53,51,50,53,50,58,
			 58,60,62,59,60,57,58,57
	};
	//对应note持续节拍数
	int [] narr = {
			 4,4,4,4,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,2,1,1,1,1,1,1,
			 2,2,2,1,1,2,2,2,1,1,2,2,2,2,2,2,2,2,4,8,4,
			 4,8,4,2,2,2,2,2,2,2,4,6,10,2,6,
			 2,4,4,2,6,2,14,4
	};

	/**
	 * 为音轨添加MidiEvent
	 * @param command  -  the MIDI command represented by this message
	 * @param channel  -  the channel associated with the message
	 * @param data1    -  音符 0-127
	 * @param data2	   -  音道 0-100 可以理解为音量
	 * @param tick 	   -  tick - the time-stamp for the event, in MIDI ticks
	 * @return
	 */
	public static MidiEvent makeEvent(int command, int channel, int data1, int data2, int tick) {
		MidiEvent event = null;
		ShortMessage a = new ShortMessage();
		try {
			a.setMessage(command, channel, data1, data2);
			event = new MidiEvent(a, tick);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		return event;
	}

	/**
	 * 播放midi音乐
	 */
	public void play() {
		try {
			Sequencer player = MidiSystem.getSequencer();
			player.open();

			Sequence seq = new Sequence(Sequence.PPQ, 10);// divisionType,
															// resolution

			Track track1 = seq.createTrack();
			Track track2 = seq.createTrack();
			Track track3 = seq.createTrack();
			Track track4 = seq.createTrack();
				
			int k = 0;
			int end = 0;
			for(int i=0;i<sqarr.length;i++){
				track1.add(makeEvent(ShortMessage.NOTE_ON, 1, sqarr[i], 70, k));
				track1.add(makeEvent(ShortMessage.NOTE_OFF, 1, sqarr[i], 50, k+narr[i]*3));
					
				k += narr[i]*3;
				end = k;
			}
			k = 48*3;
			for(int i=0;i<sqarr.length;i++){
				track2.add(makeEvent(ShortMessage.NOTE_ON, 1, sqarr[i]+6, 70, k));
				track2.add(makeEvent(ShortMessage.NOTE_OFF, 1, sqarr[i]+6, 85, k+narr[i]*3));
					
				k += narr[i]*3;
				if(k > end)
					break;
			}
			k = 96*3;
			for(int i=0;i<sqarr.length;i++){
				track3.add(makeEvent(ShortMessage.NOTE_ON, 1, sqarr[i]-14, 70, k));
				track3.add(makeEvent(ShortMessage.NOTE_OFF, 1, sqarr[i]-14, 85, k+narr[i]*3));
					
				k += narr[i]*3;
				if(k > end)
					break;
			}
			k = 154*3;
			for(int i=0;i<sqarr.length;i++){
				track4.add(makeEvent(ShortMessage.NOTE_ON, 1, sqarr[i]-4, 70, k));
				track4.add(makeEvent(ShortMessage.NOTE_OFF, 1, sqarr[i]-4, 85, k+narr[i]*3));
					
				k += narr[i]*3;
				if(k > end)
					break;
			}
				
			//保存到本地
//			File f = new File("D:\\mm6.mid");
//			MidiSystem.write(seq, 1, f);
				
			player.setSequence(seq);

			player.start();
			while (player.isRunning()) {
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			player.close();
			System.out.println("----end----");

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public static void main(String[] args) {
			
			Bmidi midi = new Bmidi();
			midi.play();
		}

}
