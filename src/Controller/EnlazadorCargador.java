/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Model.MemoryOp;
import java.util.Hashtable;
import javax.swing.RowFilter.Entry;
import java.lang.Math;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sunny
 */
public class EnlazadorCargador {
    MemoryOp MemoryZ80 = new MemoryOp();
    HexGenerator HexGen = new HexGenerator();
    public int address = 0x0;
    private boolean flag = true;
    Hashtable<String, String[]> labels = new Hashtable<String, String[]>();
    public void readAssembler(ArrayList<String> lineaslist) {
        String codeLine;
        String instruction;
        int y = 10;
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ArrayList<String> opcodeLine = new ArrayList<String>();
        
        RelocatorTable RHM = new RelocatorTable();
        RelocatorTable RHMLst = new RelocatorTable();
        String opcode;
        int n = 0;
        String OpcodeData = "";
        Integer aux = 0;
        ArrayList<String> relative_direction = new ArrayList<String>();
       for(int i=0;lineaslist.size() > i;i++){     
            instruction = lineaslist.get(i);
            codeLine = lineaslist.get(i);
            codeLine = codeLine.replaceAll(",", "");          
            codeLine = codeLine.replaceAll(":", ""); 
            codeLine = codeLine.trim();
            
            relative_direction = relativeDirections(codeLine, i);
            for(int j = 0; j < relative_direction.size(); j++){              
                opcode = relative_direction.get(j);
                opcodeLine.add(opcode);
                OpcodeData += opcode+" ";                                                                       
                n++;
            }     
            relative_direction = new ArrayList<String>();
            data.add(RHM.AddData(memoryLoadaddressHex(Integer.toHexString(aux)),OpcodeData,codeLine,instruction));
            OpcodeData = "";
            aux = n;
        }
       
              
        for(int i=0;MemoryZ80.readMemory().length > i;i++){ 
            System.out.println(MemoryZ80.readByte(i));
            /*int[] aux2;
            aux2= new int[1000];
            aux2 = MemoryZ80.readMemory();
            break;*/
            
                
        }  
       
        flag = false;
        MemoryZ80.resetMemory();     
        
        n = 0;
        address = 0x0;
        relative_direction = new ArrayList<String>();
        OpcodeData = "";
        opcodeLine = new ArrayList<String>();
        data = new ArrayList<ArrayList<String>>();
        
        for(int i=0;lineaslist.size() > i;i++){     
            instruction = lineaslist.get(i);
            codeLine = lineaslist.get(i);
            codeLine = codeLine.replaceAll(",", "");          
            codeLine = codeLine.replaceAll(":", ""); 
            codeLine = codeLine.trim();
            
            relative_direction = relativeDirections(codeLine, i);
            for(int j = 0; j < relative_direction.size(); j++){              
                opcode = relative_direction.get(j);
                opcodeLine.add(opcode);
                OpcodeData += opcode+" ";                                                                       
                n++;
            }    
            
            relative_direction = new ArrayList<String>();
            data.add(RHM.AddData(memoryLoadaddressHex(Integer.toHexString(aux)),OpcodeData,codeLine,instruction));
            OpcodeData = "";
            aux = n;
        }          
        
        RHMLst.hexMemory(this.arrayListtoString(data));
        RHMLst.pack();
        RHMLst.setVisible(true);
        RHM.hexFilled(this.arrayListtoOpcodeString(opcodeLine));
        RHM.pack();
        RHM.setVisible(true);
       

        Instructions.Intructions ins = new Instructions.Intructions(MemoryZ80.readMemory());
        ins.Ejecutar(MemoryZ80.readMemory());
                   
    }
    
        public void readAssemblerperLine(ArrayList<String> lineaslist, int linecode){
        String codeLine;
        String instruction;
        int y = 10;
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ArrayList<String> opcodeLine = new ArrayList<String>();
        
        RelocatorTable RHM = new RelocatorTable();
        RelocatorTable RHMLst = new RelocatorTable();
        String opcode;
        int n = 0;
        String OpcodeData = "";
        Integer aux = 0;
        ArrayList<String> relative_direction = new ArrayList<String>();
        for(int i=0;lineaslist.size() > i;i++){     
            instruction = lineaslist.get(i);
            codeLine = lineaslist.get(i);
            codeLine = codeLine.replaceAll(",", "");          
            codeLine = codeLine.replaceAll(":", ""); 
            codeLine = codeLine.trim();
            
            relative_direction = relativeDirections(codeLine, i);
            for(int j = 0; j < relative_direction.size(); j++){              
                opcode = relative_direction.get(j);
                opcodeLine.add(opcode);
                OpcodeData += opcode+" ";                                                                       
                n++;
            }     
            relative_direction = new ArrayList<String>();
            data.add(RHM.AddData(memoryLoadaddressHex(Integer.toHexString(aux)),OpcodeData,codeLine,instruction));
            OpcodeData = "";
            aux = n;
        }
        for(int hh = 0; hh <= MemoryZ80.readMemory().length; hh++){
            System.out.print(MemoryZ80.readMemory()[hh]);
        }  
       
        flag = false;
        MemoryZ80.resetMemory();      
        
        n = 0;
        address = 0x0;
        relative_direction = new ArrayList<String>();
        OpcodeData = "";
        opcodeLine = new ArrayList<String>();
        data = new ArrayList<ArrayList<String>>();
        
        for(int i=0;lineaslist.size() > i;i++){     
            instruction = lineaslist.get(i);
            codeLine = lineaslist.get(i);
            codeLine = codeLine.replaceAll(",", "");          
            codeLine = codeLine.replaceAll(":", ""); 
            codeLine = codeLine.trim();
            
            relative_direction = relativeDirections(codeLine, i);
            for(int j = 0; j < relative_direction.size(); j++){              
                opcode = relative_direction.get(j);
                opcodeLine.add(opcode);
                OpcodeData += opcode+" ";                                                                       
                n++;
            }    
            
            relative_direction = new ArrayList<String>();
            data.add(RHM.AddData(memoryLoadaddressHex(Integer.toHexString(aux)),OpcodeData,codeLine,instruction));
            OpcodeData = "";
            aux = n;
        }   
                    
        if (linecode == 1){          
            RHMLst.hexMemory(this.arrayListtoString(data));
            RHMLst.pack();
            RHMLst.setVisible(true);
            RHM.hexFilled(this.arrayListtoOpcodeString(opcodeLine));
            RHM.pack();
            RHM.setVisible(true);
        }else{            
            RHMLst.hexMemory(this.arrayListtoString(data), linecode);
            RHM.hexFilled(this.arrayListtoOpcodeString(opcodeLine), linecode);
        }
                  
    }
    
    public String[][] arrayListtoString(ArrayList<ArrayList<String>> param){
        String[][] data= new String[50][4];
        for(int i=0;i < param.size();i++){  
            for(int j=0;j < param.get(i).size();j++){
               data[i][j]  = param.get(i).get(j);
            }
        }
        return data;
    }
        
    public String[][] arrayListtoOpcodeString(ArrayList<String> param){
        String[][] data= new String[100][16];
        int j = 0;
        int i = 0;
        int k = 0;
        int limit = (int) Math.ceil((param.size()/16)) + 1;
        for(i = 0;i < data.length;i++){  
            for(j = 0;j < data[i].length;j++){
                data[i][j]   = "0000";
            }
        }
        j = 0;
        i = 0;
        while (i < limit && k < param.size()){
            if((j % 16) == 0 && j != 0){
                j = 0;
                i++;
                data[i][j] = param.get(k);
            }else{
               data[i][j] = param.get(k);  
               j++;
            }
            k++;
        }
        return data;
    }
    
    public ArrayList<String> relativeDirections(String instruction, Integer i){
        String[] opcode = instruction.split(" ");
        String[] memoryDirections = new String[2];
        ArrayList<String> opcodeData =  new ArrayList<String>(); 
        switch(opcode[0]){
            case "LD":
                loadLD(opcode,MemoryZ80,opcodeData);
                break;    
            case "CP":
                loadCP(opcode,MemoryZ80,opcodeData);             
                break; 
            case "JP":
                loadJP(opcode,MemoryZ80,opcodeData,labels); 
                             
            break;         
            case "JR": 
                loadJR(opcode,MemoryZ80,opcodeData,labels);                                            
            break;
            case "SUB":
                loadSUB(opcode,MemoryZ80,opcodeData);       
            break;
            case "ADD":  
                loadADD(opcode,MemoryZ80,opcodeData); 
            break;
            case "HALT":
                loadHALT(opcode,MemoryZ80,opcodeData);                
            break;
            case "DEC":
                loadDEC(opcode,MemoryZ80,opcodeData);                
            break;
            default:
                loadDEFAULT(opcode,MemoryZ80,opcodeData,flag,memoryDirections,labels);               
        }
        return opcodeData;
    }
    
    public String memoryLoadvalue(String firstno, String secondno){
        
       return ""; 
    }
    public String memoryLoadaddress(String firstno, String secondno){
        
       return ""; 
    }
    public String memoryLoadaddressHex(String firstno){
       if(firstno.length() == 1){
           return "000" + firstno;
       }
       if(firstno.length() == 2){
           return "00" + firstno;
       }
       if(firstno.length() == 3){
           return "0" + firstno;
       }
       return ""; 
    }
    
    public int get_address(){
        return this.address;
    }
    public void set_address(int i){
        this.address += i ;
    }
    
    /**
     * Carga a memoria y a interfaz grafica de los opcodes
     * @param opcode
     * @param MemoryZ80
     * @param opcodeData 
     */
    
    public void loadLD(String[] opcode,MemoryOp MemoryZ80,ArrayList opcodeData){    
        switch(opcode[1]){
            case "A":
                switch(opcode[2]){
                    case "B":                   
                    MemoryZ80.writeByte(address,0x78);
                    address += 1;
                    opcodeData.add("0x78");                 
                    break; 
                    case "C":
                    MemoryZ80.writeByte(address,0x79);
                    address += 1;
                    opcodeData.add("0x79");                                             
                    break;
                    case "D":
                    MemoryZ80.writeByte(address,0x78);
                    address += 1;
                    opcodeData.add("0x7A");                 
                    break;  
                    case "E":
                    MemoryZ80.writeByte(address,0x78);
                    address += 1;
                    opcodeData.add("0x7B");                 
                    break;  
                    case "H":
                    MemoryZ80.writeByte(address,0x78);
                    address += 1;
                    opcodeData.add("0x7C");                 
                    break;
                    case "L":
                    MemoryZ80.writeByte(address,0x78);
                    address += 1;
                    opcodeData.add("0x7D");                 
                    break;   
                    default:                          
                    MemoryZ80.writeByte(address,0x3E);
                    address += 1;      
                    opcodeData.add("0x3E");
                    MemoryZ80.writeByte(address,Integer.parseInt(opcode[2]));
                    address += 1;
                    opcodeData.add("0x"+Integer.toHexString(Integer.parseInt(opcode[2])));                                            
                    break;                         
                }
            break;   
            case "B":
                switch(opcode[2]){
                    case "A":                          
                    MemoryZ80.writeByte(address,0x47);
                    address += 1;      
                    opcodeData.add("0x47");
                    break; 
                    case "C":
                    MemoryZ80.writeByte(address,0x41);
                    address += 1;
                    opcodeData.add("0x41");                                             
                    break;
                    case "D":
                    MemoryZ80.writeByte(address,0x42);
                    address += 1;
                    opcodeData.add("0x42");                 
                    break;  
                    case "E":
                    MemoryZ80.writeByte(address,0x43);
                    address += 1;
                    opcodeData.add("0x43");                 
                    break;  
                    case "H":
                    MemoryZ80.writeByte(address,0x44);
                    address += 1;
                    opcodeData.add("0x44");                 
                    break;
                    case "L":
                    MemoryZ80.writeByte(address,0x45);
                    address += 1;
                    opcodeData.add("0x45");                 
                    break;   
                    default:
                    MemoryZ80.writeByte(address,0x06);
                    address += 1;      
                    opcodeData.add("0x06");
                    MemoryZ80.writeByte(address,Integer.parseInt(opcode[2]));
                    address += 1;
                    opcodeData.add("0x"+Integer.toHexString(Integer.parseInt(opcode[2])));                                            
                    break;
                }
                break;                  
            case "C":               
                switch(opcode[2]){
                    case "A":
                    MemoryZ80.writeByte(address,0x4F);
                    address += 1;
                    opcodeData.add("0x4F");                                            
                    break;
                    case "B":                   
                    MemoryZ80.writeByte(address,0x48);
                    address += 1;
                    opcodeData.add("0x48");                 
                    break; 
                    case "D":
                    MemoryZ80.writeByte(address,0x4A);
                    address += 1;
                    opcodeData.add("0x4A");                 
                    break;  
                    case "E":
                    MemoryZ80.writeByte(address,0x4B);
                    address += 1;
                    opcodeData.add("0x4B");                 
                    break;  
                    case "H":
                    MemoryZ80.writeByte(address,0x4C);
                    address += 1;
                    opcodeData.add("0x4C");                 
                    break;
                    case "L":
                    MemoryZ80.writeByte(address,0x4D);
                    address += 1;
                    opcodeData.add("0x4D");                 
                    break;   
                    default:                       
                    MemoryZ80.writeByte(address,0x0E);
                    address += 1;
                    opcodeData.add("0x0E");
                    MemoryZ80.writeByte(address,Integer.parseInt(opcode[2]));
                    address += 1;
                    opcodeData.add("0x"+Integer.toHexString(Integer.parseInt(opcode[2])));
                    break;
                        
                }  
            break;               
            case "D":               
                switch(opcode[2]){
                    case "A":
                    MemoryZ80.writeByte(address,0x57);
                    address += 1;
                    opcodeData.add("0x4F");                                            
                    break;
                    case "B":                   
                    MemoryZ80.writeByte(address,0x50);
                    address += 1;
                    opcodeData.add("0x50");                 
                    break; 
                    case "C":
                    MemoryZ80.writeByte(address,0x51);
                    address += 1;
                    opcodeData.add("0x51");                 
                    break;  
                    case "E":
                    MemoryZ80.writeByte(address,0x53);
                    address += 1;
                    opcodeData.add("0x53");                 
                    break;  
                    case "H":
                    MemoryZ80.writeByte(address,0x54);
                    address += 1;
                    opcodeData.add("0x54");                 
                    break;
                    case "L":
                    MemoryZ80.writeByte(address,0x55);
                    address += 1;
                    opcodeData.add("0x55");                 
                    break;   
                    default:                       
                    MemoryZ80.writeByte(address,0x16);
                    address += 1;
                    opcodeData.add("0x16");
                    MemoryZ80.writeByte(address,Integer.parseInt(opcode[2]));
                    address += 1;
                    opcodeData.add("0x"+Integer.toHexString(Integer.parseInt(opcode[2])));
                    break;
                        
                }  
            break;               
            case "E":               
                switch(opcode[2]){
                    case "A":
                    MemoryZ80.writeByte(address,0x5F);
                    address += 1;
                    opcodeData.add("0x5F");                                            
                    break;
                    case "B":                   
                    MemoryZ80.writeByte(address,0x58);
                    address += 1;
                    opcodeData.add("0x58");                 
                    break; 
                    case "C":
                    MemoryZ80.writeByte(address,0x59);
                    address += 1;
                    opcodeData.add("0x59");                 
                    break;  
                    case "D":
                    MemoryZ80.writeByte(address,0x5A);
                    address += 1;
                    opcodeData.add("0x5A");                 
                    break;  
                    case "H":
                    MemoryZ80.writeByte(address,0x5C);
                    address += 1;
                    opcodeData.add("0x5C");                 
                    break;
                    case "L":
                    MemoryZ80.writeByte(address,0x5D);
                    address += 1;
                    opcodeData.add("0x5D");                 
                    break;   
                    default:                       
                    MemoryZ80.writeByte(address,0x1E);
                    address += 1;
                    opcodeData.add("0x1E");
                    MemoryZ80.writeByte(address,Integer.parseInt(opcode[2]));
                    address += 1;
                    opcodeData.add("0x"+Integer.toHexString(Integer.parseInt(opcode[2])));
                    break;
                        
                }  
            break;               
            case "H":               
                switch(opcode[2]){
                    case "A":
                    MemoryZ80.writeByte(address,0x67);
                    address += 1;
                    opcodeData.add("0x67");                                            
                    break;
                    case "B":                   
                    MemoryZ80.writeByte(address,0x60);
                    address += 1;
                    opcodeData.add("0x60");                 
                    break; 
                    case "C":
                    MemoryZ80.writeByte(address,0x61);
                    address += 1;
                    opcodeData.add("0x61");                 
                    break;  
                    case "D":
                    MemoryZ80.writeByte(address,0x62);
                    address += 1;
                    opcodeData.add("0x62");                 
                    break;  
                    case "E":
                    MemoryZ80.writeByte(address,0x63);
                    address += 1;
                    opcodeData.add("0x63");                 
                    break;
                    case "L":
                    MemoryZ80.writeByte(address,0x65);
                    address += 1;
                    opcodeData.add("0x65");                 
                    break;   
                    default:                       
                    MemoryZ80.writeByte(address,0x26);
                    address += 1;
                    opcodeData.add("0x26");
                    MemoryZ80.writeByte(address,Integer.parseInt(opcode[2]));
                    address += 1;
                    opcodeData.add("0x"+Integer.toHexString(Integer.parseInt(opcode[2])));
                    break;
                        
                }  
            break;              
            case "L":               
                switch(opcode[2]){
                    case "A":
                    MemoryZ80.writeByte(address,0x6F);
                    address += 1;
                    opcodeData.add("0x6F");                                            
                    break;
                    case "B":                   
                    MemoryZ80.writeByte(address,0x68);
                    address += 1;
                    opcodeData.add("0x68");                 
                    break; 
                    case "C":
                    MemoryZ80.writeByte(address,0x69);
                    address += 1;
                    opcodeData.add("0x69");                 
                    break;  
                    case "D":
                    MemoryZ80.writeByte(address,0x6A);
                    address += 1;
                    opcodeData.add("0x6A");                 
                    break;  
                    case "E":
                    MemoryZ80.writeByte(address,0x6B);
                    address += 1;
                    opcodeData.add("0x6B");                 
                    break;
                    case "H":
                    MemoryZ80.writeByte(address,0x6C);
                    address += 1;
                    opcodeData.add("0x6C");                 
                    break;   
                    default:                       
                    MemoryZ80.writeByte(address,0x2E);
                    address += 1;
                    opcodeData.add("0x2E");
                    MemoryZ80.writeByte(address,Integer.parseInt(opcode[2]));
                    address += 1;
                    opcodeData.add("0x"+Integer.toHexString(Integer.parseInt(opcode[2])));
                    break;
                        
                }  
            break;               
            default:
                if (opcode[1].contains("(")){
                    MemoryZ80.writeByte(address,0x32);
                    address += 1;
                    String memoryValue=opcode[1].replace("(", "");
                    memoryValue=memoryValue.replace(")", "");
                    memoryValue=memoryValue.replace("H", "");
                    String[] memoryValueList = memoryValue.split("");
                    MemoryZ80.writeByte(address,Integer.parseInt(memoryValueList[2]+memoryValueList[3]));
                    address += 1;
                    MemoryZ80.writeByte(address,Integer.parseInt(memoryValueList[0]+memoryValueList[1]));
                    address += 1;                            
                    opcodeData.add("0x32");                           
                    opcodeData.add(""+ memoryValueList[2]+memoryValueList[3]);                            
                    opcodeData.add(""+ memoryValueList[0]+memoryValueList[1]); 
                    }
                break;
           }                           
    }
    
    
    public void loadCP(String[] opcode,MemoryOp MemoryZ80,ArrayList opcodeData){
        switch(opcode[1]){
            case "B":
                MemoryZ80.writeByte(address,0xB9);
                address += 1;
                opcodeData.add("0xB8"); 
                break; 
            case "C":
                MemoryZ80.writeByte(address,0xB9);
                address += 1;
                opcodeData.add("0xB9"); 
                break;                     
            default:                       
                if (Integer.parseInt(opcode[1]) < 255){                           
                MemoryZ80.writeByte(address,0xFE);
                address += 1;
                opcodeData.add("0xFE");
                MemoryZ80.writeByte(address,Integer.parseInt(opcode[1]));
                address += 1;
                opcodeData.add("0x"+Integer.toHexString(Integer.parseInt(opcode[1])));                                            
                break; 
            }
            break; 
                        
       }
    }
        
    public void loadJP(String[] opcode,MemoryOp MemoryZ80,ArrayList opcodeData,Hashtable<String, String[]> labels){
        switch(opcode[1]){
            case "Z":
                MemoryZ80.writeByte(address,0xCA);
                address += 1;
                opcodeData.add("0xCA");
                if(labels.get(opcode[2]) != null){                          
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[2])[0]));
                    address += 1; 
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[2])[1]));
                    address += 1; 
                    opcodeData.add(labels.get(opcode[2])[1]);   
                    opcodeData.add(labels.get(opcode[2])[0]);                                                           
                    }else{                          
                    MemoryZ80.writeByte(address,0);
                    address += 1;
                    MemoryZ80.writeByte(address,0);
                    address += 1;  
                    opcodeData.add("0");
                    opcodeData.add("0");  
                }                
                break;                             
            case "M":
                MemoryZ80.writeByte(address,0xFA);
                opcodeData.add("0xFA");
                address += 1;
                if(labels.get(opcode[2]) != null){                          
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[2])[0]));
                    address += 1; 
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[2])[1]));
                    address += 1;  
                    opcodeData.add(labels.get(opcode[2])[1]);   
                    opcodeData.add(labels.get(opcode[2])[0]);                              
                }else{                          
                    MemoryZ80.writeByte(address,0);
                    address += 1;           
                    MemoryZ80.writeByte(address,0);
                    address += 1;
                    opcodeData.add("0");
                    opcodeData.add("0");   
                }                        
                break;
            default:
                MemoryZ80.writeByte(address,0xC3);
                address += 1;                      
                opcodeData.add("0xC3"); 
                if(labels.get(opcode[1]) != null){                  
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[1])[0]));
                    address += 1; 
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[1])[1]));
                    address += 1;  
                    opcodeData.add(labels.get(opcode[1])[1]);   
                    opcodeData.add(labels.get(opcode[1])[0]);                       
                    }else{                          
                    MemoryZ80.writeByte(address,0);
                    address += 1;            
                    MemoryZ80.writeByte(address,0);
                    address += 1;  
                    opcodeData.add("0"); 
                    opcodeData.add("0"); 
                }                       
                break;                       
            case "P": 
                MemoryZ80.writeByte(address,0xF2);
                opcodeData.add("0xF2");
                address += 1;
                if(labels.get(opcode[2]) != null){                          
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[2])[0]));
                    address += 1; 
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[2])[1]));
                    address += 1;  
                    opcodeData.add(labels.get(opcode[2])[1]);   
                    opcodeData.add(labels.get(opcode[2])[0]);                              
                    }else{                          
                    MemoryZ80.writeByte(address,0);
                    address += 1;           
                    MemoryZ80.writeByte(address,0);
                    address += 1;
                    opcodeData.add("0");
                    opcodeData.add("0");   
                }            
            break;                     
        }  
    }
    public void loadJR(String[] opcode,MemoryOp MemoryZ80,ArrayList opcodeData,Hashtable<String, String[]> labels){
        switch(opcode[1]){
            case "Z":
                MemoryZ80.writeByte(address,0x28);
                address += 1;
                opcodeData.add("0x28");
                if(labels.get(opcode[2]) != null){                          
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[2])[0]));
                    address += 1; 
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[2])[1]));
                    address += 1; 
                    opcodeData.add(labels.get(opcode[2])[1]);   
                    opcodeData.add(labels.get(opcode[2])[0]);                                                          
                    }else{                          
                    MemoryZ80.writeByte(address,0);
                    address += 1;
                    MemoryZ80.writeByte(address,0);
                    address += 1;  
                    opcodeData.add("0");
                    opcodeData.add("0");  
                }                
                break;                               
            case "NZ":
                MemoryZ80.writeByte(address,0x20);
                opcodeData.add("0x20");
                address += 1;
                if(labels.get(opcode[2]) != null){                          
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[2])[0]));
                    address += 1; 
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[2])[1]));
                    address += 1;  
                    opcodeData.add(labels.get(opcode[2])[1]);   
                    opcodeData.add(labels.get(opcode[2])[0]);                              
                    }else{                          
                    MemoryZ80.writeByte(address,0);
                    address += 1;           
                    MemoryZ80.writeByte(address,0);
                    address += 1;
                    opcodeData.add("0");
                    opcodeData.add("0");   
                }                        
                break;
            default:
                MemoryZ80.writeByte(address,0x18);
                address += 1;                      
                opcodeData.add("0x18"); 
                if(labels.get(opcode[1]) != null){                  
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[1])[0]));
                    address += 1; 
                    MemoryZ80.writeByte(address,Integer.valueOf(labels.get(opcode[1])[1]));
                    address += 1;  
                    opcodeData.add(labels.get(opcode[1])[1]);   
                    opcodeData.add(labels.get(opcode[1])[0]);                       
                    }else{                          
                    MemoryZ80.writeByte(address,0);
                    address += 1;            
                    MemoryZ80.writeByte(address,0);
                    address += 1;  
                    opcodeData.add("0"); 
                    opcodeData.add("0"); 
                }                       
            break;                       
        } 
    }
    
    public void loadSUB(String[] opcode,MemoryOp MemoryZ80,ArrayList opcodeData){
        switch(opcode[1]){
            case "C":
                MemoryZ80.writeByte(address,0x91);
                address += 1;
                opcodeData.add("0x91");                        
                break;
            case "B":
                MemoryZ80.writeByte(address,0x90);
                address += 1;   
                opcodeData.add("0x90");                       
                break;
        }   
    } 
    public void loadADD(String[] opcode,MemoryOp MemoryZ80,ArrayList opcodeData){        
        switch(opcode[1]){
            case "A": 
                switch(opcode[2]){
                    case "B":                               
                    MemoryZ80.writeByte(address,0x80);
                    address += 1;
                    opcodeData.add("0x80");                 
                    break;
                    case "C":  
                    MemoryZ80.writeByte(address,0x81);
                    address += 1;
                    opcodeData.add("0x81");                                             
                    break;
                    default:
                    MemoryZ80.writeByte(address,0x3E);
                    address += 1;      
                    opcodeData.add("0x3E");
                    MemoryZ80.writeByte(address,Integer.parseInt(opcode[2]));
                    address += 1;
                    opcodeData.add("0x"+Integer.toHexString(Integer.parseInt(opcode[2])));                                            
                    break;                              
                }                                       
            break;        
        }
    }
    
    public void loadHALT(String[] opcode,MemoryOp MemoryZ80,ArrayList opcodeData){   
        MemoryZ80.writeByte(address,0x76);
        address += 1;  
        opcodeData.add("0x76");  
    }
    
    public void loadDEC(String[] opcode,MemoryOp MemoryZ80,ArrayList opcodeData){        
        switch(opcode[1]){
            case "C":
                MemoryZ80.writeByte(address,0x0D);
                address += 1;
                opcodeData.add("0x0D");                        
                break;
            case "B":
                MemoryZ80.writeByte(address,0x05);
                address += 1;   
                opcodeData.add("0x05");                       
                break;
        }  
    } 
    public void loadDEFAULT(String[] opcode,MemoryOp MemoryZ80
            ,ArrayList opcodeData,boolean flag,String[] memoryDirections,
            Hashtable<String, String[]>  labels){   
        if(flag){
            if (opcode[0].equals("MAIN")){
                memoryDirections[0] = Integer.toHexString(0)+'0';
                memoryDirections[1] = Integer.toHexString(0)+'0';
                labels.put(opcode[0], memoryDirections);  
                }else{
                memoryDirections[1] = Integer.toString(0)+'0';
                memoryDirections[0] = Integer.toString(address);
                labels.put(opcode[0], memoryDirections);       
            } 
        } 
    }
    
}


