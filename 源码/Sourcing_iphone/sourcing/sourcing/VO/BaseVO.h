//
//  BaseVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import <Foundation/Foundation.h>

@class BaseVO;
typedef void(^DetailInfoCompleteBlock)(BaseVO *kvo);
typedef void (^DetailInfoErrorBlock)(NSString *kmsg);

@interface BaseVO : NSObject
{
    NSInteger _id;
    DetailInfoCompleteBlock _comblock;
    DetailInfoErrorBlock _errorblock;
}

@property NSInteger Id;

-(NSString *)getHtmlTitle;
-(NSString *)getHtmlSubTitle;
-(void)setHtmlToShow:(DetailInfoCompleteBlock)kcomblock failure:(DetailInfoErrorBlock)kerrorblock;
-(NSString *)generateShareText;
-(NSString *)getHtmlContext;
@end
