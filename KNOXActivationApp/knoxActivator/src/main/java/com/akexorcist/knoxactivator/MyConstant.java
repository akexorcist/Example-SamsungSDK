/**
 * DISCLAIMER: PLEASE TAKE NOTE THAT THE SAMPLE APPLICATION AND
 * SOURCE CODE DESCRIBED HEREIN IS PROVIDED FOR TESTING PURPOSES ONLY.
 *
 * Samsung expressly disclaims any and all warranties of any kind,
 * whether express or implied, including but not limited to the implied warranties and conditions
 * of merchantability, fitness for a particular purpose and non-infringement.
 * Further, Samsung does not represent or warrant that any portion of the sample application and
 * source code is free of inaccuracies, errors, bugs or interruptions, or is reliable,
 * accurate, complete, or otherwise valid. The sample application and source code is provided
 * "as is" and "as available", without any warranty of any kind from Samsung.
 *
 * Your use of the sample application and source code is at its own discretion and risk,
 * and licensee will be solely responsible for any damage that results from the use of the sample
 * application and source code including, but not limited to, any damage to your computer system or
 * platform. For the purpose of clarity, the sample code is licensed “as is” and
 * licenses bears the risk of using it.
 *
 * Samsung shall not be liable for any direct, indirect or consequential damages or
 * costs of any type arising out of any action taken by you or others related to the sample application
 * and source code.
 */

package com.akexorcist.knoxactivator;

//This interface captures all constants used in the project

public interface MyConstant {

	/***********************************************************************************
	 * Please insert ELM key here for the test.
	 * It is recommended not to save ELM key in the apk but to obtain it from server.
	 **********************************************************************************/
	String ELM_KEY = "C9BA38BFB9967E5FE515782500ED6EC36ABCF1EE0B3BC11A61141041E129F78C6F07AA79543581BD237CC5606DF14BC5D3F515EBE3BBCE99445B190F0973D8C0";

	String MY_PREFS_NAME = "SampleApps";
	String ADMIN = "admin";
	String ELM = "elm";
	String DISABLE_ADMIN_WARNING = "";
	String DO_SELF_UNINSTALL = "do self uninstall";

	int RESULT_ENABLE_ADMIN = 1;
	int RESULT_DISABLE_ADMIN = 2;
	int RESULT_ELM_ACTIVATED = 3;
	int DEFAULT_ERROR = -888;

	public enum MDMVersion {
		VER_2_0, VER_2_1, VER_2_2, VER_3_0, VER_4_0, VER_4_0_1, VER_4_1, VER_5_0, VER_5_1, VER_5_2, VER_5_3, VER_5_4, VER_5_4_1, VER_5_5, NONE
	}

}