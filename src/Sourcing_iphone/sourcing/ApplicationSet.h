//
//  ApplicationSet.h
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "UserVO.h"

#define CINT_BG_HEX 0xe9f2fc

//需要#import "UIColor+MGExpanded.h"
#define CCOLOR_BG_WHITE  [UIColor colorWithRGBHex:CINT_BG_HEX]
#define CCOLOR_TABLEVIEW_BG [UIColor colorWithRGBHex:CINT_BG_HEX]
#define CCOLOR_TABLEVIEW_SEL [UIColor colorWithRGBHex:0xFFb6c5f3]
#define CCOLOR_TABLEVIEW_SEL_2 [UIColor colorWithRGBHex:0xFFb6c5f3]

@interface ApplicationSet : NSObject


//所有的栏目列表
@property (nonatomic,strong)NSArray *channels;

-(void)setDeviceToken:(NSString *)deviceToken;

+(ApplicationSet *)shareData;

-(void)setLoginUserInfo:(UserVO *)kuserVO saveinfo:(BOOL)ksave;
-(UserVO *)getUserVO;
@end
