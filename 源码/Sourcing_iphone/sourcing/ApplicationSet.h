//
//  ApplicationSet.h
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "UserVO.h"

#define CINT_BG_HEX 0xfcf2e9

//需要#import "UIColor+MGExpanded.h"
#define CCOLOR_BG_WHITE  [UIColor colorWithRGBHex:CINT_BG_HEX]
#define CCOLOR_TABLEVIEW_BG [UIColor colorWithRGBHex:CINT_BG_HEX]
#define CCOLOR_TABLEVIEW_SEL [UIColor colorWithRGBHex:0xFFF3C5B6]
#define CCOLOR_TABLEVIEW_SEL_2 [UIColor colorWithRGBHex:0xFFFBC5B6]

@interface ApplicationSet : NSObject

//是否已经注册过设备号
@property BOOL isRegDevToken;
//设备号
@property (nonatomic,strong)NSString *deviceToken;
//所有的栏目列表
@property (nonatomic,strong)NSArray *channels;

+(ApplicationSet *)shareData;

-(void)setLoginUserInfo:(UserVO *)kuserVO saveinfo:(BOOL)ksave;
-(UserVO *)getUserVO;
@end
