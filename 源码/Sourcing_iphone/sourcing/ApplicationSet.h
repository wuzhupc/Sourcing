//
//  ApplicationSet.h
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ApplicationSet : NSObject
{
    //是否已经注册过设备号
    BOOL _isRegDevToken;
    
    NSString *_deviceToken;
}

@property BOOL isRegDevToken;
@property (nonatomic) NSString *deviceToken;


+(ApplicationSet *)shareData;
@end
